package pe.edu.upc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Empleo;
import pe.edu.upc.entity.Postulante;
import pe.edu.upc.service.IEmpleoService;

@Controller
@RequestMapping("/postulante")
@SessionAttributes("postulante")
public class PostulanteController {

	@Autowired
	private IEmpleoService empleoService;

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Postulante postulante = empleoService.findPostulanteById(id);

		if (postulante == null) {
			flash.addFlashAttribute("error", "El postulante no existe en la base de datos!");
			return "redirect:/empleo/listar";
		}
		model.addAttribute("postulante", postulante);
		model.addAttribute("titulo", "Postulante: " + postulante.getNombrePostulante());

		return "postulante/ver";
	}

	@GetMapping("/form/{empleoId}")
	public String crear(@PathVariable(value = "empleoId") Long empleoId, Map<String, Object> model,
			RedirectAttributes flash) {

		Empleo empleo = empleoService.findOne(empleoId);

		if (empleo == null) {
			flash.addFlashAttribute("error", "El empleo no existe en la base de datos");
			return "redirect:/empleo/listar";
		}

		Postulante postulante = new Postulante();
		postulante.setEmpleo(empleo);

		model.put("postulante", postulante);
		model.put("titulo", "Registrar Postulante");

		return "postulante/form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Postulante postulante, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Postular");
			return "postulante/form";
		}

		empleoService.savePostulante(postulante);
		status.setComplete();

		return "redirect:/index";

	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		Postulante postulante = empleoService.findPostulanteById(id);

		if (postulante != null) {
			empleoService.deletePostulante(id);
			flash.addFlashAttribute("success", "Postulante eliminado con éxito!");
			return "redirect:/empleo/ver/" + postulante.getEmpleo().getId();
		}
		flash.addFlashAttribute("error", "El postulante no existe en la base de datos, no se pudo eliminar!");

		return "redirect:/empleo/listar";
	}

}
