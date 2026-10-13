package com.uberits.uberitspring.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.modelos.RestauranteViewModel;

@Controller
@RequestMapping("/")
public class IndexController {
	@GetMapping
	public String index(Model modelo) {
		var restaurantes = List.of(
				RestauranteViewModel.builder().nombre("BurgerMediterranean")
						.tiposComida(List.of("Americana", "Mediterranea")).build(),
				RestauranteViewModel.builder().nombre("PizzaTV").tiposComida(List.of("Italiana")).build());

		modelo.addAttribute("restaurantes", restaurantes);

		return "index";
	}
}
