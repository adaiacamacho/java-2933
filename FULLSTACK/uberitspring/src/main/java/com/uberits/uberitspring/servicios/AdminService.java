package com.uberits.uberitspring.servicios;

import com.uberits.uberitspring.dtos.RestauranteEntradaDto;
import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.dtos.RestauranteSalidaDto;
import com.uberits.uberitspring.dtos.TipoComidaDto;

public interface AdminService {

	Iterable<RestauranteListadoDto> listadoRestaurantes();

	void borrarRestaurante(Long id);

	Iterable<TipoComidaDto> listadoTiposComida();

	RestauranteSalidaDto crearRestaurante(RestauranteEntradaDto aDto);

}
