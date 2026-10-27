package com.uberits.uberitspring.rest;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.uberits.uberitspring.servicios.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioRestController {
	private final AnonimoService anonimoService;

	private final AuthenticationConfiguration authenticationConfiguration;
	private final JwtService jwtService;

	@PostMapping("/login")
	public Map<String, String> login(@Valid @RequestBody UsuarioAutenticarEntradaDto dto) {
		System.out.println("LOGIN: " + dto);
		
		Authentication authentication = authenticationConfiguration.getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

		System.out.println("AUTHENTICATION: "+ authentication);

		String token = jwtService.generarToken(authentication.getName());

		return Map.of("accessToken", token, "tokenType", "Bearer");
	}

	@GetMapping("prueba")
	public String pruebaSeguridad() {
		return "OK";
	}

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
