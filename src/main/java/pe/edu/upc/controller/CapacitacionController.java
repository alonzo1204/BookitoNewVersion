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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Capacitacion;
import pe.edu.upc.paginator.PageRender;
import pe.edu.upc.service.IAreaService;
import pe.edu.upc.service.ICapacitacionService;

@Controller
@SessionAttributes("capacitacion")
@RequestMapping("/capacitacion")
public class CapacitacionController {

	// se injecta en ICapacitacionService
	@Autowired
	private ICapacitacionService capacitacionService;

	// se injecta en IAreaService
	@Autowired
	private IAreaService areaService;

	// METODO PARA LISTAR CAPACITACIONES
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		// Sirve para designar cuantos elementos van a entrar en una pagina(4)
		Pageable pageRequest = new PageRequest(page, 4);

		Page<Capacitacion> capacitaciones = capacitacionService.findAll(pageRequest);
		// en la pagina se van a listar las capacitaciones
		PageRender<Capacitacion> pageRender = new PageRender<Capacitacion>("/listarcapacitacion", capacitaciones);
		model.addAttribute("titulo", "Listado de Capacitaciones");
		model.addAttribute("capacitaciones", capacitaciones);
		model.addAttribute("page", pageRender);
		
		return "capacitacion/listar";
	}

	// METODO PARA ENTRAR AL FORMULARIO DE CAPACITACION
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		//se crea un objeto capacitacion
		Capacitacion capacitacion = new Capacitacion();
		//se almacenan todos los datos a capacitacion
		model.put("capacitacion", capacitacion);
		//se listan en el formulario las areas
		model.put("listaAREAS", areaService.listar());
		//se coloca el titulo al formulario
		model.put("titulo", "Registrar Capacitacion");
		//esta es la pagina a donde se dirige (carpeta:capacitacion html:form)
		return "capacitacion/form";
	}

	// METODO PARA EDITAR LA CAPACITACION
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Capacitacion capacitacion = null;

		if (id > 0) {
			capacitacion = capacitacionService.findOne(id);
			model.put("listaAREAS", areaService.listar());
			if (capacitacion == null) {
				flash.addFlashAttribute("error", "El ID de la capacitacion no existe en la Base de datos!");
				return "redirect:/capacitacion/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de la capacitacion no puede ser cero!");
			return "redirect:/capacitacion/listar";
		}
		model.put("capacitacion", capacitacion);
		model.put("titulo", "Editar Capacitacion");
		return "capacitacion/form";

	}

	// METODO PARA GUARDAR LA CAPACITACION
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Capacitacion capacitacion, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Capacitacion");
			return "capacitacion/form";
		}
		
		String mensajeFlash = (capacitacion.getId() != null) ? "Capacitacion editada con exito!"
				: "Capacitacion creada con exito!";

		model.addAttribute("listaAREAS", areaService.listar());
		capacitacionService.save(capacitacion);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/capacitacion/listar";

	}

	// METODO PARA ELIMINAR LA CAPACITACION
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			capacitacionService.delete(id);
			flash.addFlashAttribute("success", "Capacitación eliminado con Éxito!");
		}
		return "redirect:/capacitacion/listar";
	}

	// METODO PARA BUSCAR
	@RequestMapping(value = "/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Capacitacion capacitacion) throws ParseException {

		List<Capacitacion> listaCapacitaciones = null;

		
		capacitacion.setTemaCapacitacion(capacitacion.getTemaCapacitacion());
		listaCapacitaciones = capacitacionService.buscarTema(capacitacion.getTemaCapacitacion());

		if (listaCapacitaciones.isEmpty()) {
			model.put("titulo", "Buscar Capacitación");
			model.put("mensaje", "No se encontro");
		}
		model.put("titulo", "Buscar Capacitación");
		model.put("listaCapacitaciones", listaCapacitaciones);
		return "capacitacion/buscar";

	}

	// METODO PARA ENTRAR A BUSCAR CAPACITACION
	@RequestMapping(value = "/irBuscar")
	public String irBuscar(Model model) {
		
		model.addAttribute("titulo", "Buscar Capacitación");
		model.addAttribute("capacitacion", new Capacitacion());
		return "capacitacion/buscar";
	}

}
