package com.uberits.uberitspring.servicios.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uberits.uberitspring.dtos.RestauranteDetalleDto;
import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.dtos.UsuarioAutenticarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioAutenticarSalidaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarSalidaDto;
import com.uberits.uberitspring.entidades.Usuario;
import com.uberits.uberitspring.mappers.RestauranteMapper;
import com.uberits.uberitspring.mappers.UsuarioMapper;
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
	private final UsuarioMapper usuarioMapper;
	private final RestauranteMapper restauranteMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UsuarioRegistrarSalidaDto registrarse(@Valid UsuarioRegistrarEntradaDto usuarioDto) {
		log.info("Se ha registrado un nuevo usuario");

		Usuario usuario = usuarioMapper.aEntidad(usuarioDto);
		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		var usuarioRegistrado = usuarioRepository.save(usuario);

		return usuarioMapper.aDtoRegistro(usuarioRegistrado);
	}

	@Override
	public Optional<UsuarioAutenticarSalidaDto> autenticarse(UsuarioAutenticarEntradaDto usuarioDto) {
		Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuarioDto.email());

		if (usuarioEmail.isEmpty() || !usuarioEmail.get().getPassword().equals(usuarioDto.password())) {
			return Optional.empty();
		}

		return Optional.of(usuarioMapper.aDtoAutenticacion(usuarioEmail.get()));
	}

	@Override
	public Collection<RestauranteListadoDto> listadoRestaurantes() {
		return restauranteMapper.aDto(restauranteRepository.findAll());
	}

	@Override
	public Optional<RestauranteDetalleDto> verRestaurante(Long id) {
		return restauranteMapper.aDtoDetalle(restauranteRepository.findById(id));
	}
}
