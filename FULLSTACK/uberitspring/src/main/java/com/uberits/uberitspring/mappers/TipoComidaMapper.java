package com.uberits.uberitspring.mappers;

import org.mapstruct.Mapper;

import com.uberits.uberitspring.dtos.TipoComidaDto;
import com.uberits.uberitspring.entidades.TipoComida;

@Mapper(componentModel = "spring")
public interface TipoComidaMapper {

	Iterable<TipoComidaDto> aDto(Iterable<TipoComida> tiposComida);

}
