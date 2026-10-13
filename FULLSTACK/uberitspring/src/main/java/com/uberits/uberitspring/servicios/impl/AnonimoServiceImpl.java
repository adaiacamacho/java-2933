package com.uberits.uberitspring.servicios.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.entidades.Restaurante;
import com.uberits.uberitspring.entidades.Usuario;
import com.uberits.uberitspring.repositorios.RestauranteRepository;
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
	private final RestauranteRepository restauranteRepository;

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
		return StreamSupport.stream(restauranteRepository.findAll().spliterator(), false).map(this::aDto).toList();
	}

	private RestauranteListadoDto aDto(Restaurante restaurante) {
		return RestauranteListadoDto.builder().nombre(restaurante.getNombre())
				.tiposComida(restaurante.getTiposComida().stream().map(tc -> tc.getNombre()).toList()).build();
	}
}
