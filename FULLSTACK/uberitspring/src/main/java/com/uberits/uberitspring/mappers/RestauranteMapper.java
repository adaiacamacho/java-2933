package com.uberits.uberitspring.mappers;

import java.util.Collection;

import org.mapstruct.Mapper;

import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.entidades.Restaurante;
import com.uberits.uberitspring.entidades.TipoComida;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

	RestauranteListadoDto aDto(Restaurante restaurante);

	Collection<RestauranteListadoDto> aDto(Collection<Restaurante> restaurantes);
	
	default Collection<String> mapTiposComida(Collection<TipoComida> tipos) {
		return tipos.stream().map(TipoComida::getNombre).toList();
	}
}