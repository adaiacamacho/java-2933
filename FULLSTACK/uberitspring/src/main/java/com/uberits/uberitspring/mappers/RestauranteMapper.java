package com.uberits.uberitspring.mappers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uberits.uberitspring.dtos.RestauranteDetalleDto;
import com.uberits.uberitspring.dtos.RestauranteEntradaDto;
import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.dtos.RestauranteSalidaDto;
import com.uberits.uberitspring.entidades.Restaurante;
import com.uberits.uberitspring.entidades.TipoComida;
import com.uberits.uberitspring.modelos.RestauranteFormModel;
import com.uberits.uberitspring.modelos.RestauranteViewModel;

import jakarta.validation.Valid;

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

	RestauranteEntradaDto aDto(@Valid RestauranteFormModel restauranteFormModel);

	@Mapping(ignore = true, target = "tiposComida")
	@Mapping(ignore = true, target = "platos")
	Restaurante aEntidad(RestauranteEntradaDto restauranteDto);

	@Mapping(ignore = true, target = "tiposComidaIds")
	RestauranteSalidaDto aDto(Restaurante restauranteCreado);

	@Mapping(target = "tiposComidaIds", source = "tiposComida") //al no poder hacerlo solo, usa directamente el metodo default
	RestauranteFormModel aFormModel(Restaurante restaurante);
	
	default List<Long> mapTiposComidaToIds(Collection<TipoComida> tiposComida) {
        if (tiposComida == null) {
            return null; //si el restaurante no tiene tiposComida, devuelve null
        }
        return tiposComida.stream() 
                .map(TipoComida::getId) //devuelve una lista de los ids, mapeando cada tipocomida
                .collect(Collectors.toList());
    }
}