package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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

import pe.edu.upc.entity.Empleado;
import pe.edu.upc.paginator.PageRender;
import pe.edu.upc.service.IAreaService;
import pe.edu.upc.service.IEmpleadoService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@SessionAttributes("empleado")
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private IAreaService areaService;

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

	// METODO PARA VER EL DETALLE EMPLEADO
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		// Obtenemos el Empleado por el ID
		Empleado empleado = empleadoService.findOne(id);

		// Validamos el empleado
		if (empleado == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos");
			return "redirect:/empleado/listar";
		}

		model.put("empleado", empleado);
		model.put("titulo", "Detalle del empleado: " + empleado.getNombreEmpleado());

		return "empleado/ver";
	}

	// METODO PARA LISTAR LOS EMPLEADOS
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = new PageRequest(page, 20);

		Page<Empleado> empleados = empleadoService.findAll(pageRequest);

		PageRender<Empleado> pageRender = new PageRender<Empleado>("empleado/listar", empleados);
		model.addAttribute("titulo", "Listado de Empleados");
		model.addAttribute("empleados", empleados);
		model.addAttribute("page", pageRender);
		return "empleado/listar";
	}

	// METODO PARA INGRESAR A CREAR EMPLEADO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Empleado empleado = new Empleado();
		model.put("empleado", empleado);
		
		model.put("listaAREAS", areaService.listar());
		
		model.put("titulo", "Registrar Empleado");
		return "empleado/form";
	}

	// METODO PARA EDITAR EMPLEADO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Empleado empleado = null;

		if (id > 0) {
			empleado = empleadoService.findOne(id);
			model.put("listaAREAS", areaService.listar());
			if (empleado == null) {
				flash.addFlashAttribute("error", "El ID del empleado no existe en la Base de datos!");
				return "redirect:/empleado/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del empleado no puede ser cero!");
			return "redirect:/empleado/listar";
		}
		model.put("empleado", empleado);
		model.put("titulo", "Editar Empleado");
		return "empleado/form";
	}

	// METODO PARA GUARDAR EMPLEADO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Empleado empleado, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empleado");
			return "empleado/form";
		}

		// si el campo foto No esta vacio
		if (!foto.isEmpty()) {

			if (empleado.getId() != null && empleado.getId() > 0 && empleado.getFoto() != null
					&& empleado.getFoto().length() > 0) {

				uploadFileService.delete(empleado.getFoto());
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
			// pasar el nombre de la Foto al Empleado
			empleado.setFoto(uniqueFilename);
		}

		// validar si es guardar o editar mediante el id para mostrar el mensajeFlash
		String mensajeFlash = (empleado.getId() != null) ? "Empleado editado con �xito!" : "Empleado creado con �xito!";

		model.addAttribute("listaAREAS", areaService.listar());
		empleadoService.save(empleado);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/empleado/listar";

	}

	//METODO PARA ELIMINAR EMPLEADO
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Empleado empleado = empleadoService.findOne(id);

			empleadoService.delete(id);
			flash.addFlashAttribute("success", "Empleado eliminado con exito!");

			if (uploadFileService.delete(empleado.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + empleado.getFoto() + " eliminada con exito!");
			}
		}
		return "redirect:/empleado/listar";
	}
	
	//METODO PARA IR A LA PANTALLA DE BSUCAR
	@RequestMapping(value = "/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("titulo", "Buscar Empleado");
		model.addAttribute("empleado", new Empleado());
		return "empleado/buscar";
	}
	
	// METODO PARA BUSCAR
	@RequestMapping(value = "/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Empleado empleado) throws ParseException {
		
		List<Empleado> listaEmpleados;
		
		if (StringUtils.isNumeric(empleado.getDniEmpleado())) {
			listaEmpleados=empleadoService.findByDniEmpleado(empleado.getDniEmpleado());
		} else  {
			empleado.setNombreEmpleado(empleado.getDniEmpleado());
			listaEmpleados=empleadoService.buscarNombre(empleado.getNombreEmpleado());
			
			if(listaEmpleados.isEmpty()) {
				try {
					listaEmpleados=empleadoService.findBynombreArea(empleado.getDniEmpleado());
				}catch (Exception e) {
					model.put("mensaje", "Formato incorreco");
				}
			}

		}
		
		if(listaEmpleados.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		
		
		model.put("titulo", "Buscar Empleado");
		model.put("listaEmpleados", listaEmpleados);
		return  "empleado/buscar";
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
