package com.redreal.datajpa.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redreal.datajpa.app.models.entity.Cliente;
import com.redreal.datajpa.app.models.entity.Factura;
import com.redreal.datajpa.app.models.entity.Product;
import com.redreal.datajpa.app.service.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") Long clienteId, 
			Model model, 
			RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(clienteId);
		if(cliente == null) {
			flash.addAttribute("error", "Cliente no existe");
			return "redirect:/listar";
		}
		
		Factura factura = new Factura();
		factura.setClient(cliente);
		
		model.addAttribute("factura", factura);
		model.addAttribute("titulo"	, "Crear Factura");
		return "factura/form";		
	}
	
	@GetMapping(value="/cargar-productos/{text}", produces = {"application/json"}) 
	public @ResponseBody List<Product> cargarProductos (@PathVariable(name = "text") String text){
		System.out.println(text);
		return clienteService.findByName(text);		
	}

}
