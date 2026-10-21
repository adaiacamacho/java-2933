package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final AnonimoService anonimoService;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("restaurantes", anonimoService.listadoRestaurantes());
		
		return "admin/index";
	}
}
