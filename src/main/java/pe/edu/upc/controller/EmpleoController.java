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

import pe.edu.upc.entity.Empleo;
import pe.edu.upc.paginator.PageRender;
import pe.edu.upc.service.IAreaService;
import pe.edu.upc.service.IEmpleoService;
import pe.edu.upc.service.ITipoEmpleoService;

@Controller
@SessionAttributes("empleo")
@RequestMapping("/empleo")
public class EmpleoController {

	@Autowired
	private IEmpleoService empleoService;

	@Autowired
	private IAreaService areaService;

	@Autowired
	private ITipoEmpleoService tipoempleoService;

	// METODO PARA VER EL DETALLE DEL EMPLEO
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		// obtenemos el Empleo por ID
		Empleo empleo = empleoService.findOne(id);

		// validamos el empleo
		if (empleo == null) {
			flash.addFlashAttribute("error", "El empleo no existe en la base de datos");
			return "redirect:/empleo/listar";
		}

		model.put("empleo", empleo);
		model.put("titulo", "Detalle del empleo: " + empleo.getNombreEmpleo());

		return "empleo/ver";
	}

	// METODO PARA LISTAR LOS EMPLEOS
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = new PageRequest(page, 4);

		Page<Empleo> empleos = empleoService.findAll(pageRequest);

		PageRender<Empleo> pageRender = new PageRender<>("empleo/listar", empleos);
		model.addAttribute("titulo", "Listado de Empleos");
		model.addAttribute("empleos", empleos);
		model.addAttribute("page", pageRender);

		return "empleo/listar";
	}

	// METODO PARA INGRESAR A CREAR
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Empleo empleo = new Empleo();
		model.put("empleo", empleo);

		model.put("listaAREAS", areaService.listar());
		model.put("listaTIPOEMPLEOS", tipoempleoService.listar());

		model.put("titulo", "Registrar Empleo");
		return "empleo/form";
	}

	// METODO PARA EDITAR EMPLEO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Empleo empleo = null;

		if (id > 0) {
			empleo = empleoService.findOne(id);
			model.put("listaAREAS", areaService.listar());
			model.put("listaTIPOEMPLEOS", tipoempleoService.listar());

			if (empleo == null) {
				flash.addFlashAttribute("error", "El ID del empleo no existe en la Base de datos!");
				return "redirect:/empleo/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del empleo no puede ser cero!");
			return "redirect:/empleo/listar";
		}

		model.put("empleo", empleo);
		model.put("titulo", "Editar Empleo");
		return "empleo/form";
	}

	// METODO PARA GUARDAR EMPLEO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Empleo empleo, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empleo");
			return "empleo/form";
		}

		String mensajeFlash = (empleo.getId() != null) ? "Empleo editado con �xito!" : "Empleo creado con exito!";

		model.addAttribute("listaAREAS", areaService.listar());
		model.addAttribute("listaTIPOEMPLEOS", tipoempleoService.listar());

		empleoService.save(empleo);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/empleo/listar";
	}

	// METODO PARA ELIMINAR EMPLEO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Empleo empleo = empleoService.findOne(id);

			empleoService.delete(id);
			flash.addFlashAttribute("success", "Empleo eliminado con exito!");
		}
		return "redirect:/empleo/listar";
	}

	// METODO PARA IR A LA PANTALLA DE BSUCAR
	@RequestMapping(value = "/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("titulo", "Buscar Empleo");
		model.addAttribute("empleo", new Empleo());
		return "empleo/buscar";
	}

	// METODO PARA BUSCAR
	@RequestMapping(value = "/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Empleo empleo) throws ParseException {

		List<Empleo> listaEmpleos;
		
		empleo.setNombreEmpleo(empleo.getNombreEmpleo());
		listaEmpleos= empleoService.buscarNombre(empleo.getNombreEmpleo());
		
		if(listaEmpleos.isEmpty()) {
			listaEmpleos=empleoService.findByNombreTipoEmpleo(empleo.getNombreEmpleo());
			
			if(listaEmpleos.isEmpty()) {
				try {
					listaEmpleos=empleoService.findByNombreArea(empleo.getNombreEmpleo());
				}catch (Exception e)  {
					model.put("mensaje", "Formato incorreco");
					
				}
			}
		}
		
		if(listaEmpleos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		
		model.put("titulo", "Buscar Empleo");
		model.put("listaEmpleos", listaEmpleos);
		return  "empleo/buscar";
	}
	
}
