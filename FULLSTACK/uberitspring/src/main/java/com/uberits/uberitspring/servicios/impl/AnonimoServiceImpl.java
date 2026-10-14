package com.uberits.uberitspring.servicios.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.dtos.UsuarioAutenticarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioAutenticarSalidaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarSalidaDto;
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
	public UsuarioRegistrarSalidaDto registrarse(@Valid UsuarioRegistrarEntradaDto usuarioDto) {
		var usuario = Usuario.builder().nombre(usuarioDto.nombre()).email(usuarioDto.email())
				.password(usuarioDto.password()).build();

		log.info("Se ha registrado un nuevo usuario");

		var usuarioRegistrado = usuarioRepository.save(usuario);

		return UsuarioRegistrarSalidaDto.builder().id(usuarioRegistrado.getId()).nombre(usuarioRegistrado.getNombre())
				.email(usuarioRegistrado.getEmail()).build();
	}

	@Override
	public Optional<UsuarioAutenticarSalidaDto> autenticarse(UsuarioAutenticarEntradaDto usuarioDto) {
		var usuario = Usuario.builder().email(usuarioDto.email()).password(usuarioDto.password()).build();

		Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioEmail.isEmpty() || !usuarioEmail.get().getPassword().equals(usuario.getPassword())) {
			return Optional.empty();
		}

		return Optional.of(UsuarioAutenticarSalidaDto.builder().email(usuarioEmail.get().getEmail())
				.nombre(usuarioEmail.get().getNombre()).build());
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
