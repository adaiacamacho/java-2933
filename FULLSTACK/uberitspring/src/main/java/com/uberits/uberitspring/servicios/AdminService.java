package com.uberits.uberitspring.servicios;

import com.uberits.uberitspring.dtos.RestauranteListadoDto;

public interface AdminService {

	Iterable<RestauranteListadoDto> listadoRestaurantes();

	void borrarRestaurante(Long id);

}
