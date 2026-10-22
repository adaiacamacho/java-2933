package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.servicios.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("restaurantes", adminService.listadoRestaurantes());
		
		return "admin/index";
	}
	
	@GetMapping("restaurantes/borrar")
	public String borrarRestaurante(Long id) {
		adminService.borrarRestaurante(id);
		
		return "redirect:/admin";
	}
}
