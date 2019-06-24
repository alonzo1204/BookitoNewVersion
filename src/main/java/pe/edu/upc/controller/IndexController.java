package pe.edu.upc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Empleo;
import pe.edu.upc.service.IEmpleoService;

@Controller
public class IndexController {
	
	@Autowired
	private IEmpleoService empleoService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		
		List<Empleo> empleos2= empleoService.findAll();
		model.addAttribute("empleos2", empleos2);
		
		return "index";
	}
	
	@RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		// obtenemos el Empleo por ID
		Empleo empleo = empleoService.findOne(id);


		model.put("empleo", empleo);
		model.put("titulo", "Detalle del empleo: " + empleo.getNombreEmpleo());

		return "/ver";
	}

}
