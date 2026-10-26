package com.uberits.uberitspring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uberits.uberitspring.dtos.AlertaDto;
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

	@GetMapping("restaurante/{id}")
	public String restaurante(@PathVariable Long id, Model modelo) {
		var restaurante = anonimoService.verRestaurante(id);

		modelo.addAttribute("restaurante", restaurante.orElse(null));

		return "restaurante";
	}

	@GetMapping("registro")
	public String registro(UsuarioFormModel usuarioFormModel) {
		return "registro";
	}

	@PostMapping("registro")
	public String registroPost(Model modelo, @Valid UsuarioFormModel usuarioFormModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			modelo.addAttribute("alerta", AlertaDto.builder()
					.mensaje("Hay errores en los campos de usuario introducidos").nivel("danger").build());
			
			return "registro";
		}

		anonimoService.registrarse(usuarioMapper.aDtoRegistro(usuarioFormModel));

		redirectAttributes.addFlashAttribute("alerta",
				AlertaDto.builder().mensaje("Usuario " + usuarioFormModel.getNombre() + " registrado correctamente")
						.nivel("success").build());

		return "redirect:/";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
}
