package com.uberits.uberitspring.servicios;

import java.util.Optional;

import com.uberits.uberitspring.dtos.PlatoDetalleDto;

public interface UsuarioService {

	Optional<PlatoDetalleDto> verPlato(Long idPlato);
	
}
