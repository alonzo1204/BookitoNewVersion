package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Informe;
import pe.edu.upc.paginator.PageRender;
import pe.edu.upc.service.ICapacitacionService;
import pe.edu.upc.service.IInformeService;

@Controller
@SessionAttributes("informe")
@RequestMapping("/informe")
public class InformeController {

	@Autowired
	private IInformeService informeService;

	@Autowired
	private ICapacitacionService capacitacionService;

	// METODO PARA VER EL DETALLE DEL INFORME
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		// obtenemos el informe por ID
		Informe informe = informeService.findOne(id);

		// validamos el informe
		if (informe == null) {
			flash.addFlashAttribute("error", "El informe no existe en la base de datos");
			return "redirect:/informe/listar";
		}

		model.put("informe", informe);
		model.put("titulo", "Detalle del informe: " + informe.getTituloInforme());

		return "informe/ver";
	}

	// METODO PARA LISTAR LOS INFORMES
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = new PageRequest(page, 4);

		Page<Informe> informes = informeService.findAll(pageRequest);

		PageRender<Informe> pageRender = new PageRender<>("informe/listar", informes);
		model.addAttribute("titulo", "Listado de Informes");
		model.addAttribute("informes", informes);
		model.addAttribute("page", pageRender);

		return "informe/listar";
	}

	// METODO PARA INGRESAR A CREAR
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Informe informe = new Informe();
		model.put("informe", informe);

		model.put("listaCAPACITACIONES", capacitacionService.findAll());

		model.put("titulo", "Registrar Informe");
		return "informe/form";
	}

	// METODO PARA EDITAR INFORME
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Informe informe = null;

		if (id > 0) {
			informe = informeService.findOne(id);
			model.put("listaCAPACITACIONES", capacitacionService.findAll());
			if (informe == null) {
				flash.addFlashAttribute("error", "El ID del informe no existe en la Base de datos!");
				return "redirect:/informe/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del informe no puede ser cero!");
			return "redirect:/informe/listar";
		}

		model.put("informe", informe);
		model.put("titulo", "Editar Informe");
		return "informe/form";
	}

	// METODO PARA GUARDAR EMPLEADO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Informe informe, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Informe");
			return "informe/form";
		}

		String mensajeFlash = (informe.getId() != null) ? "Informe editado con �xito!" : "Informe creado con exito!";

		model.addAttribute("listaCAPACITACIONES", capacitacionService.findAll());
		informeService.save(informe);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/informe/listar";
	}

	// METODO PARA ELIMINAR INFORME
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Informe informe = informeService.findOne(id);

			informeService.delete(id);
			flash.addFlashAttribute("success", "Informe eliminado con exito!");
		}
		return "redirect:/informe/listar";
	}

	// METODO PARA IR A LA PANTALLA DE BSUCAR
	@RequestMapping(value = "/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("titulo", "Buscar Informe");
		model.addAttribute("informe", new Informe());
		return "informe/buscar";
	}

	// METODO PARA BUSCAR
	@RequestMapping(value = "/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Informe informe) throws ParseException {

		List<Informe> listaInformes;
		
		informe.setTituloInforme(informe.getTituloInforme());
		listaInformes=informeService.buscarTitulo(informe.getTituloInforme());
		
		if(listaInformes.isEmpty()) {
			try {
				listaInformes=informeService.findByTemaCapacitacion(informe.getTituloInforme());
			}catch (Exception e)  {
				model.put("mensaje", "Formato incorreco");
				
			}
		}
		
		if(listaInformes.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		
		model.put("titulo", "Buscar Informe");
		model.put("listaInformes", listaInformes);
		return  "informe/buscar";
	}
}
