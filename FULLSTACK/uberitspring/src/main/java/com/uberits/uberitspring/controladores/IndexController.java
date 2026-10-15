package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uberits.uberitspring.mappers.RestauranteMapper;
import com.uberits.uberitspring.mappers.UsuarioMapper;
import com.uberits.uberitspring.modelos.UsuarioFormModel;
import com.uberits.uberitspring.servicios.AnonimoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class IndexController {
	private final AnonimoService anonimoService;
	private final RestauranteMapper restauranteMapper;
	private final UsuarioMapper usuarioMapper;

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

	@GetMapping("/registro")
	public String registro(UsuarioFormModel usuarioFormModel) {
		return "registro";
	}

	@PostMapping("/registro")
	public String registroPost(Model modelo, @Valid UsuarioFormModel usuarioFormModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registro";
		}

		anonimoService.registrarse(usuarioMapper.aDtoRegistro(usuarioFormModel));

		return "redirect:/";
	}

}
