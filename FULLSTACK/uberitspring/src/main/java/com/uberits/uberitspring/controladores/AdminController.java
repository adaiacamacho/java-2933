package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.mappers.RestauranteMapper;
import com.uberits.uberitspring.modelos.RestauranteFormModel;
import com.uberits.uberitspring.servicios.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;
	
	private final RestauranteMapper restauranteMapper;
	
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
	
	@GetMapping("restaurantes/formulario")
	public String formularioRestaurante(RestauranteFormModel restauranteFormModel, Model modelo) {
		var tiposComida = adminService.listadoTiposComida();
		
		modelo.addAttribute("tiposComida", tiposComida);
		
		return "admin/restaurantes/formulario";
	}
	
	@PostMapping("restaurantes/formulario")
	public String formularioRestaurantePost(@Valid RestauranteFormModel restauranteFormModel, BindingResult bindingResult, Model modelo) {
		if (bindingResult.hasErrors()) {
			var tiposComida = adminService.listadoTiposComida();
			modelo.addAttribute("tiposComida", tiposComida);
			
			return "admin/restaurantes/formulario";
		}
		
		adminService.crearRestaurante(restauranteMapper.aDto(restauranteFormModel));
		
		return "redirect:/admin";
	}
}
