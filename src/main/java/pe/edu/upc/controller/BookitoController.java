package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Bookito;
import pe.edu.upc.paginator.PageRender;
import pe.edu.upc.service.IBookitoService;
import pe.edu.upc.service.ICategoriaService;
import pe.edu.upc.service.ISedeService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@SessionAttributes("bookito")
@RequestMapping("/bookito")
public class BookitoController {

	@Autowired
	private IBookitoService	bookitoService;
	
	@Autowired
	private ISedeService sedeService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	// METODO PARA VER EL DETALLE Bookito
		@GetMapping(value = "/ver/{id}")
		public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

			// Obtenemos el Bookito por el ID
			
			Bookito bookito = bookitoService.findOne(id);

			// Validamos el bookito
			if (bookito == null) {
				flash.addFlashAttribute("error", "El bookito no existe en la base de datos");
				return "redirect:/bookito/listar";
			}

			model.put("bookito", bookito);
			model.put("titulo", "Detalle de libro: " + bookito.getTitulo());

			return "bookito/ver";
		}

		// METODO PARA LISTAR LOS Bookitos
		//@Secured("ROLE_ADMIN")
		@RequestMapping(value = "/listar", method = RequestMethod.GET)
		public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

			Pageable pageRequest = new PageRequest(page, 20);

			Page<Bookito> bookitos = bookitoService.findAll(pageRequest);
		

			PageRender<Bookito> pageRender = new PageRender<Bookito>("bookito/listar", bookitos);
			model.addAttribute("titulo", "Listado de Bookitos");
			model.addAttribute("bookitos", bookitos);
			model.addAttribute("page", pageRender);
			return "bookito/listar";
		}
		
		// METODO PARA INGRESAR A CREAR Bookito
		//@Secured("ROLE_ADMIN")
		@RequestMapping(value = "/form")
		public String crear(Map<String, Object> model) {

			Bookito bookito = new Bookito();
			model.put("bookito", bookito);
			
			model.put("listaSEDES", sedeService.listar());
			model.put("listaCATEGORIAS", categoriaService.listar());
			
			model.put("titulo", "Registrar Bookito");
			return "bookito/form";
		}
	
		// METODO PARA EDITAR Bookito
		//@Secured("ROLE_ADMIN")
		@RequestMapping(value = "/form/{id}")
		public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

			Bookito bookito = null;

			if (id > 0) {
				bookito = bookitoService.findOne(id);
				model.put("listaSEDES", sedeService.listar());
				model.put("listaCATEGORIAS", categoriaService.listar());
				if (bookito== null) {
					flash.addFlashAttribute("error", "El ID del bookito no existe en la Base de datos!");
					return "redirect:/bookito/listar";
				}
			} else {
				flash.addFlashAttribute("error", "El ID del bookito no puede ser cero!");
				return "redirect:/bookito/listar";
			}
			model.put("bookito", bookito);
			model.put("titulo", "Editar Bookito");
			return "bookito/form";
		}
	
		// METODO PARA GUARDAR Bookito
		//@Secured("ROLE_ADMIN")
		@RequestMapping(value = "/form", method = RequestMethod.POST)
		public String guardar(@Valid Bookito bookito, BindingResult result, Model model,
				@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

			if (result.hasErrors()) {
				model.addAttribute("titulo", "Formulario Bookito");
				return "bookito/form";
			}

			// si el campo foto No esta vacio
			if (!foto.isEmpty()) {

				if (bookito.getId() != null && bookito.getId() > 0 && bookito.getFoto() != null
						&& bookito.getFoto().length() > 0) {

					uploadFileService.delete(bookito.getFoto());
				}

				////
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				// pasar el nombre de la Foto al bookito
				bookito.setFoto(uniqueFilename);
			}

			// validar si es guardar o editar mediante el id para mostrar el mensajeFlash
			String mensajeFlash = (bookito.getId() != null) ? "Bookito editado con �xito!" : "Bookito creado con �xito!";

			model.addAttribute("listaSEDES", sedeService.listar());
			model.addAttribute("listaCATEGORIAS", categoriaService.listar());
			bookitoService.save(bookito);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			return "redirect:/bookito/listar";

		}
		
		//METODO PARA ELIMINAR Bookito
		//@Secured("ROLE_ADMIN")
		@RequestMapping(value = "/eliminar/{id}")
		public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {
				Bookito bookito = bookitoService.findOne(id);

				bookitoService.delete(id);
				flash.addFlashAttribute("success", "bookito eliminado con exito!");

				if (uploadFileService.delete(bookito.getFoto())) {
					flash.addFlashAttribute("info", "Foto " + bookito.getFoto() + " eliminada con exito!");
				}
			}
			return "redirect:/bookito/listar";
		}
		
		
		//METODO PARA IR A LA PANTALLA DE BUSCAR
		@RequestMapping(value = "/irBuscar")
		public String irBuscar(Model model) {
			model.addAttribute("titulo", "Buscar Bookito");
			model.addAttribute("bookito", new Bookito());
			return "bookito/buscar";
		}
		

		@RequestMapping(value = "/buscar")
		public String buscar(Map<String, Object> model, @ModelAttribute Bookito bookito) throws ParseException {

			List<Bookito> listaBookitos;
			//bookito.setAutor(bookito.getAutor());
			//listaBookitos= bookitoService.buscarAutor(bookito.getAutor());
			//bookito.setAutor(bookito.getIsbn());
			//listaBookitos= bookitoService.buscarAutor(bookito.getIsbn());
			
			//bookito.setIsbn(bookito.getIsbn());
			//listaBookitos= bookitoService.buscarIsbn(bookito.getIsbn());
			
			
			bookito.setTitulo(bookito.getTitulo());
			listaBookitos= bookitoService.buscarAutor(bookito.getTitulo());
			
			
			
			if(listaBookitos.isEmpty()) {
				listaBookitos=bookitoService.buscarSede(bookito.getTitulo());
				
				if(listaBookitos.isEmpty()) {
					
					try {
						listaBookitos=bookitoService.buscarCategoria(bookito.getTitulo());
					}catch (Exception e)  {
						model.put("mensaje", "Formato incorreco");
						
					}
				}
			}
			
	
			
			
			
			
		
			if(listaBookitos.isEmpty()) {
				model.put("mensaje", "No se encontro");
			}
			
			
			model.put("titulo", "Buscar Bookito");
			model.put("listaBookitos", listaBookitos);
			return  "bookito/buscar";
		}
		
	
}
