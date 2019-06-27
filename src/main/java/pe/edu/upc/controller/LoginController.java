package pe.edu.upc.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Usuario;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) Usuario usuario,String logout, Model model, Principal principal,
			RedirectAttributes flash,SessionStatus status) {
		
		
		
		
		
		
		if (principal != null) {
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/empleo/listar";
		}

		if (error != null) {
			model.addAttribute("error","Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
			return "login";
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
			return "login";
		}
		
	
		return "login";

	}
	
	@RequestMapping("/reglamento")
	public String irReglamento() {
		return "reglamento";
	}
	@RequestMapping("/principal")
	public String irPrincipal() {
		return "principal";
	}
}
