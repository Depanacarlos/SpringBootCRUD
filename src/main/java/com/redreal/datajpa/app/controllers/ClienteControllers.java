package com.redreal.datajpa.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redreal.datajpa.app.models.entity.Cliente;
import com.redreal.datajpa.app.service.IClienteService;
import com.redreal.datajpa.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteControllers {
	
	@Autowired
	private IClienteService clienteService;

	private String tipo;
	private String mensaje;
	
	@RequestMapping(value = "/listar",method = RequestMethod.GET)
	public String listar (@RequestParam(name = "page", defaultValue = "0") int page,Model model) {	
		Pageable pageRequest = PageRequest.of(page, 3);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender <Cliente> pageRender = new PageRender("/listar", clientes);
				
		model.addAttribute("titulo","Listado de Clientes");
		model.addAttribute("clientes",clientes);
		model.addAttribute("page",pageRender);
		return "listar";
	}
	
	@GetMapping("/form")
	public String crear(Cliente cliente, Model model, SessionStatus status) {
		model.addAttribute("titulo","Formulario de Cliente");
		model.addAttribute("cliente",cliente);
		model.addAttribute("btn","Crear cliente");
		status.setComplete();
		return "form";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result,Model model,@RequestParam("file") MultipartFile foto, SessionStatus status, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Ah ocurrido un error");
			model.addAttribute("btn","Crear cliente");
			return "form";
		}		
		Cliente editCliente = cliente;
		
		if(cliente.getId() != null) {
			editCliente = clienteService.findOne(cliente.getId());}
		if(!foto.isEmpty()) {			
			
			//Validad si se quiere editar un cliente con foto, actualizar foto y borrar anterior
			
			if(editCliente.getId() != null
					&& editCliente.getId() > 0
					&& editCliente.getFoto() !=null
					&& editCliente.getFoto().length() >0) {
				
				Path rutaImagen = Paths.get("uploads").resolve(editCliente.getFoto()).toAbsolutePath();
				try {
					Files.deleteIfExists(rutaImagen);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			//ruta dentro del proyecto
			//Path rutaRecursos = Paths.get("src//main//resources//static/upload");
			//String rootPath = rutaRecursos.toFile().getAbsolutePath();
			
			//ruta desacoplada del proyecto, ruta externa
			//String rootPath = "c://temp/uploads";
			
			//ruta absoluta en el proyecto
			//crear nombre unico con UUID
			String uniqueFileName = UUID.randomUUID().toString().concat("_").concat(foto.getOriginalFilename());
			
			Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
			Path absolutePath = rootPath.toAbsolutePath();			
			
			try {
//				byte[] bytes = foto.getBytes();
//				Path rutaGuardado = Paths.get(rootPath.concat("//").concat(foto.getOriginalFilename()));
//				Files.write(rutaGuardado, bytes);
				Files.copy(foto.getInputStream(),absolutePath);
				cliente.setFoto(uniqueFileName);
				flash.addFlashAttribute("warning","Imagen guardada con éxito ".concat(uniqueFileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("vacio");
		}
		
		if(cliente.getId() != null) {
			tipo = "info";
			mensaje = "Cliente editado con éxito";
		}else {
			tipo = "success";
			mensaje = "Cliente creado con éxito";			
		}
		
		clienteService.save(cliente);
		flash.addFlashAttribute(tipo,mensaje);
		status.setComplete();
		return "redirect:listar";		
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable("id") Long id, Model model, RedirectAttributes flash, SessionStatus status ) {
		Cliente cliente = clienteService.findOne(id);
		
		if(cliente == null) {
			flash.addFlashAttribute("error","El cliente no existe en la BD");
			return "redirect:listar";
		}
		
		model.addAttribute("cliente",cliente);
		model.addAttribute("titulo","Detalle Cliente: ".concat(cliente.getNombre()));
		status.setComplete();
		return "ver";
	}
	
	@GetMapping("/form/{id}")
	public String editar (@PathVariable Long id,Cliente cliente, Model model, SessionStatus status) {
		cliente = null;
		if(id>0) {
			cliente = clienteService.findOne(id);
		}
		model.addAttribute("btn","Editar cliente");
		model.addAttribute("cliente",cliente);
		model.addAttribute("titulo","Editar Cliente ".concat(cliente.getNombre()));
		status.setComplete();
		return "form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar (@PathVariable Long id, RedirectAttributes flash) {
		
		if(id>0) {
			Cliente cliente = clienteService.findOne(id);
			String filename = cliente.getFoto();
			if(!filename.isEmpty()) {
				Path rutaImagen = Paths.get("uploads").resolve(filename).toAbsolutePath();
				//System.out.println(rutaImagen);
				try {
					Files.deleteIfExists(rutaImagen);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			clienteService.delete(id);
			flash.addFlashAttribute("danger","Cliente eliminado");
		}		
		return "redirect:/listar";
	}
	
	
	//Cargar imagen HTTP ResponsiveEntity
	@GetMapping("/upload/{filename:.+}")
	public ResponseEntity<Resource> verFoto (@PathVariable String filename) {
		
		//rutaAbosoluta de la imagen
		Path ruta = Paths.get("uploads").resolve(filename).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(ruta.toUri());
			
			if(!recurso.exists() && !recurso.isReadable()) {
				throw new RuntimeException("Error: no se puede acceder al archivo: "+filename);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"")
				.body(recurso);
		
	}

}