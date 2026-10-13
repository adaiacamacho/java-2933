package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.modelos.RestauranteViewModel;
import com.uberits.uberitspring.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class IndexController {
	private final AnonimoService anonimoService;

	@GetMapping
	public String index(Model modelo) {
		Iterable<RestauranteViewModel> restaurantes = anonimoService.listadoRestaurantes().stream()
				.map(dto -> RestauranteViewModel.builder().nombre(dto.nombre()).tiposComida(dto.tiposComida()).build())
				.toList();

		modelo.addAttribute("restaurantes", restaurantes);

		return "index";
	}
}
