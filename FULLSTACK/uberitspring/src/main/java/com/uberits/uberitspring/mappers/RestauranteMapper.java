package com.uberits.uberitspring.mappers;

import java.util.Collection;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.uberits.uberitspring.dtos.RestauranteDetalleDto;
import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.entidades.Restaurante;
import com.uberits.uberitspring.entidades.TipoComida;
import com.uberits.uberitspring.modelos.RestauranteViewModel;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

	RestauranteListadoDto aDtoListado(Restaurante restaurante);

	Collection<RestauranteListadoDto> aDto(Collection<Restaurante> restaurantes);
	
	default Collection<String> mapTiposComida(Collection<TipoComida> tipos) {
		return tipos.stream().map(TipoComida::getNombre).toList();
	}

	RestauranteDetalleDto aDtoDetalle(Restaurante restaurante);
	
	default Optional<RestauranteDetalleDto> aDtoDetalle(Optional<Restaurante> restaurante) {
        return restaurante.map(this::aDtoDetalle);
    }

	Collection<RestauranteViewModel> aViewModel(Collection<RestauranteListadoDto> listadoRestaurantes);
}