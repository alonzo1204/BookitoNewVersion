package pe.edu.upc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Bookito;
import pe.edu.upc.entity.Usuario;
import pe.edu.upc.paginator.PageRender;
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
	
	
	// METODO PARA VER EL DETALLE usuario
			@GetMapping(value = "/informacion/{id}")
			public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

				// Obtenemos el Bookito por el ID
				
				Usuario usuario = usuarioService.findOne(id);

				

				model.put("usuario", usuario);
				model.put("titulo", "Detalle de usuario: " + usuario.getUsername());

				return "informacion/ver";
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

				String mensajeFlash = (usuario.getId() != null) ? "" :  "Bienvenido, "+ usuario.getUsername() ;
				
				
				usuarioService.save(usuario);
				
				
				status.setComplete();
				flash.addFlashAttribute("success", mensajeFlash);
				return "redirect:/login";
			}

			// METODO PARA EDITAR usuario
			//@Secured("ROLE_ADMIN")
			@RequestMapping(value = "/recuperacion/{id}")
			public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

				Usuario usuario = null;

				if (id > 0) {
					usuario = usuarioService.findOne(id);
					
				
				} 
				model.put("usuario", usuario);
				model.put("titulo", "Editar Usuario");
				return "recuperacion";
			}
			
			// METODO PARA LISTAR LOS Bookitos
			//@Secured("ROLE_ADMIN")
			@RequestMapping(value = "/usuario", method = RequestMethod.GET)
			public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

				Pageable pageRequest = new PageRequest(page, 20);

				Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
			

				PageRender<Usuario> pageRender = new PageRender<Usuario>("/usuario", usuarios);
				model.addAttribute("titulo", "Listado de usuarios");
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("page", pageRender);
				return "usuario";
			}
			
			//METODO PARA ELIMINAR Bookito
			@Secured("ROLE_ADMIN")
			@RequestMapping(value = "/eliminar/{id}")
			public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

				if (id > 0) {
					Usuario usuario = usuarioService.findOne(id);

					usuarioService.delete(id);
					flash.addFlashAttribute("success", "bookito eliminado con exito!");

					
				}
				return "redirect:/usuario";
			}
			
			
}

