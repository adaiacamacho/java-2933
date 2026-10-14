package com.uberits.uberitspring.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.uberits.uberitspring.dtos.UsuarioAutenticarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioAutenticarSalidaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarSalidaDto;
import com.uberits.uberitspring.servicios.AnonimoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioRestController {
	private final AnonimoService anonimoService;

	@PostMapping("autenticar")
	public UsuarioAutenticarSalidaDto autenticar(@Valid @RequestBody UsuarioAutenticarEntradaDto usuarioDto) {
		var usuarioAutenticado = anonimoService.autenticarse(usuarioDto);

		if (usuarioAutenticado.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

		return usuarioAutenticado.get();
	}

	@PostMapping("registrar")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioRegistrarSalidaDto registrar(@Valid @RequestBody UsuarioRegistrarEntradaDto usuarioDto) {
		return anonimoService.registrarse(usuarioDto);
	}
}
