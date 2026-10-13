package com.uberits.uberitspring.servicios.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.entidades.Usuario;
import com.uberits.uberitspring.repositorios.UsuarioRepository;
import com.uberits.uberitspring.servicios.AnonimoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Validated

@Log
@RequiredArgsConstructor

@Service
public class AnonimoServiceImpl implements AnonimoService {
	private final UsuarioRepository usuarioRepository;

	@Override
	public Usuario registrarse(@Valid Usuario usuario) {
		log.info("Se ha registrado un nuevo usuario");

		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> autenticarse(Usuario usuario) {
		Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioEmail.isEmpty() || !usuarioEmail.get().getPassword().equals(usuario.getPassword())) {
			return Optional.empty();
		}

		return usuarioEmail;
	}

	@Override
	public Collection<RestauranteListadoDto> listadoRestaurantes() {
		return List.of(
				RestauranteListadoDto.builder().nombre("Burger Mediterranean")
						.tiposComida(List.of("Americana", "Mediterranea")).build(),
				RestauranteListadoDto.builder().nombre("Pizza TV").tiposComida(List.of("Italiana")).build());
	}

}
