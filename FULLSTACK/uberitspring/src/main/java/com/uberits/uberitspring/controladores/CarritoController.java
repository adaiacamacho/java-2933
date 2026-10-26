package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.mappers.PlatoMapper;
import com.uberits.uberitspring.modelos.CarritoViewModel;
import com.uberits.uberitspring.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/usuario/carrito")
public class CarritoController {
	private final CarritoViewModel carritoViewModel;
	private final UsuarioService usuarioService;
	private final PlatoMapper platoMapper;
	
	@GetMapping
	public String carrito(Model modelo) {
		modelo.addAttribute("carrito", carritoViewModel);
		
		return "usuario/carrito";
	}
	
	@GetMapping("agregar")
	public String carritoAgregar(Long id) {
		var plato = usuarioService.verPlato(id);
		
		carritoViewModel.getLineas().add(platoMapper.aViewModel(plato.get()));
		
		return "redirect:/usuario/carrito";
	}
}
