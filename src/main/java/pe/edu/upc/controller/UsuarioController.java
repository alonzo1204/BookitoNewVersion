package pe.edu.upc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pe.edu.upc.entity.Usuario;

import pe.edu.upc.service.IUsuarioService;

@Controller
@SessionAttributes("usuario")
@RequestMapping("")
public class UsuarioController {
	

	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// METODO PARA INGRESAR A CREAR USUARIO
			
			@RequestMapping(value = "/registro")
			public String crear(Map<String, Object> model) {

				Usuario usuario = new Usuario();
				model.put("usuario", usuario);
				
				
				model.put("titulo", "Registrar Usuario");
				return "registro";
			}
			// METODO PARA GUARDAR USUARIO
		
			@RequestMapping(value = "/registro", method = RequestMethod.POST)
			public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
					SessionStatus status) {
				
				
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
				if (result.hasErrors()) {
					model.addAttribute("titulo", "Formulario de Usuario");
					return "/registro";
				}

				String mensajeFlash = (usuario.getId() != null) ? "" : "Usuario creado con exito!";
				
				
				usuarioService.save(usuario);
				
				
				status.setComplete();
				flash.addFlashAttribute("success", mensajeFlash);
				return "redirect:/bookito/listar";
			}

			
			
}

