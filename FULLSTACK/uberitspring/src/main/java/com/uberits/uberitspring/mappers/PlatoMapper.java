package com.uberits.uberitspring.mappers;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uberits.uberitspring.dtos.PlatoDetalleDto;
import com.uberits.uberitspring.entidades.Plato;
import com.uberits.uberitspring.modelos.CarritoViewModel.LineaViewModel;

@Mapper(componentModel = "spring")
public interface PlatoMapper {

	default Optional<PlatoDetalleDto> aDtoDetalle(Optional<Plato> plato) {
		return plato.map(this::aDtoDetalle);
	}

	PlatoDetalleDto aDtoDetalle(Plato plato);

	@Mapping(source = "id", target = "idPlato")
	@Mapping(source = "nombre", target = "nombrePlato")
	@Mapping(source = "precio", target = "precioPlato")
	@Mapping(target = "cantidad", ignore = true)
	LineaViewModel aViewModel(PlatoDetalleDto platoDetalleDto);
}