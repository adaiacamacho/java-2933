package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.modelos.CarritoViewModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	private final CarritoViewModel carritoViewModel;
	
	@GetMapping("carrito")
	public String carrito(Model modelo) {
		modelo.addAttribute("carrito", carritoViewModel);
		
		return "usuario/carrito";
	}
}
