package com.uberits.uberitspring.servicios;

import java.util.Collection;
import java.util.Optional;

import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.entidades.Usuario;

import jakarta.validation.Valid;

public interface AnonimoService {
	Usuario registrarse(@Valid Usuario usuario);
	Optional<Usuario> autenticarse(Usuario usuario);
	Collection<RestauranteListadoDto> listadoRestaurantes();
}
