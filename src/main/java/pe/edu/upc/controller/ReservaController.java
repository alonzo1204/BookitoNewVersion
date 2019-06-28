package pe.edu.upc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Bookito;
import pe.edu.upc.entity.Reserva;
import pe.edu.upc.entity.Sede;
import pe.edu.upc.paginator.PageRender;
import pe.edu.upc.service.IBookitoService;
import pe.edu.upc.service.ICategoriaService;
import pe.edu.upc.service.IReservaService;
import pe.edu.upc.service.ISedeService;
import pe.edu.upc.service.IUsuarioService;

@Controller
@SessionAttributes("reserva")
@RequestMapping("/reserva")
public class ReservaController {
	@Autowired
	private ISedeService sedeService;
	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IReservaService reservaService;
	@Autowired
	private IBookitoService bookitoService;
	@Autowired
	private IUsuarioService usuarioService;
			

	
	
	// METODO PARA VER EL DETALLE RESERVA
			@GetMapping(value = "/ver/{id}")
			public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

				// Obtenemos el Bookito por el ID
				Reserva reserva = reservaService.findOne(id);
			

				// Validamos el bookito
				if (reserva == null) {
					flash.addFlashAttribute("error", "La reserva no existe en la base de datos");
					return "redirect:/reserva/listar";
				}

				model.put("reserva", reserva);
				model.put("titulo", "Detalle de Reserva: ");

				return "reserva/ver";
			}
	
			// METODO PARA LISTAR LOS RESERVA
			@RequestMapping(value = "/listar", method = RequestMethod.GET)
			public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

				Pageable pageRequest = new PageRequest(page, 20);

				Page<Reserva> reservas = reservaService.findAll(pageRequest);
			

				PageRender<Reserva> pageRender = new PageRender<Reserva>("reserva/listar", reservas);
				model.addAttribute("titulo", "Historial de Reservas");
				model.addAttribute("reservas", reservas);
				model.addAttribute("page", pageRender);
				return "reserva/listar";
			}
	

		
			// METODO PARA INGRESAR A CREAR Bookito
						//@Secured("ROLE_ADMIN")
					@RequestMapping(value = "/form")
					public String crear(Map<String, Object> model) {
							
						Reserva reserva = new Reserva();
						reserva.setFechainicial(Calendar.getInstance().getTime());
						
						model.put("reserva", reserva);
						
							
						model.put("listaBOOKITOS", bookitoService.listar());
						model.put("listaUSUARIOS", usuarioService.listar());
							
						model.put("titulo", "Registrar reserva");
							return "reserva/form";
					}
					
					@RequestMapping(value = "/form/{bookitoId}")
					public String crearBookito(Map<String, Object> model, @PathVariable Long bookitoId) {
							
						//Reserva reserva = reservaService.findOne(bookitoId);
						Reserva reserva = new Reserva();
						reserva.setBookito(bookitoService.findOne(bookitoId));
					
				        
				        
						reserva.setFechainicial(Calendar.getInstance().getTime());
						reserva.setFechafinal(Calendar.getInstance().getTime());
						model.put("reserva", reserva);
							
						model.put("listaBOOKITOS", bookitoService.listar());
						model.put("listaUSUARIOS", usuarioService.listar());
							
						model.put("titulo", "Registrar reserva");
							return "reserva/form";
					}
				
			
			// METODO PARA GUARDAR reserva
			//@Secured("ROLE_ADMIN")
			@RequestMapping(value = "/form", method = RequestMethod.POST)
			public String guardar(@Valid Reserva reserva, BindingResult result, Model model,
					 RedirectAttributes flash, SessionStatus status) {

				if (result.hasErrors()) {
					model.addAttribute("titulo", "Formulario Reserva");
					return "reserva/form";
				}

				
					
				

				// validar si es guardar o editar mediante el id para mostrar el mensajeFlash
				String mensajeFlash = (reserva.getId() != null) ? "reserva editado con �xito!" : "reserva creado con exito!";

				model.addAttribute("listaBOOKITOS", bookitoService.listar());
				model.addAttribute("listaUSUARIOS", usuarioService.listar());
				reservaService.save(reserva);
				status.setComplete();
				flash.addFlashAttribute("success", mensajeFlash);
				return "redirect:/reserva/listar";

			}
			
	
			
			
			
}
