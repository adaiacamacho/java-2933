package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.mappers.RestauranteMapper;
import com.uberits.uberitspring.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class IndexController {
	private final AnonimoService anonimoService;
	private final RestauranteMapper restauranteMapper;

	@GetMapping
	public String index(Model modelo) {
		var restaurantes = restauranteMapper.aViewModel(anonimoService.listadoRestaurantes());

		modelo.addAttribute("restaurantes", restaurantes);

		return "index";
	}
	
	@GetMapping("{id}")
	public String restaurante(@PathVariable Long id, Model modelo) {
		var restaurante = anonimoService.verRestaurante(id);
		
		modelo.addAttribute("restaurante", restaurante.orElse(null));
		
		return "restaurante";
	}
}
