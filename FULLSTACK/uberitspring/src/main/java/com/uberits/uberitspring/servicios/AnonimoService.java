package com.uberits.uberitspring.servicios;

import java.util.Collection;
import java.util.Optional;

import com.uberits.uberitspring.dtos.RestauranteDetalleDto;
import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.dtos.UsuarioAutenticarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioAutenticarSalidaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarSalidaDto;

import jakarta.validation.Valid;

public interface AnonimoService {
	UsuarioRegistrarSalidaDto registrarse(@Valid UsuarioRegistrarEntradaDto usuario);
	Optional<UsuarioAutenticarSalidaDto> autenticarse(UsuarioAutenticarEntradaDto usuario);
	Collection<RestauranteListadoDto> listadoRestaurantes();
	Optional<RestauranteDetalleDto> verRestaurante(Long id);
}
