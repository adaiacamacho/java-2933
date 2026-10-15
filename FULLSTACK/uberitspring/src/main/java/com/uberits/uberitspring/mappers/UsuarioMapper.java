package com.uberits.uberitspring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uberits.uberitspring.dtos.UsuarioAutenticarSalidaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarEntradaDto;
import com.uberits.uberitspring.dtos.UsuarioRegistrarSalidaDto;
import com.uberits.uberitspring.entidades.Usuario;
import com.uberits.uberitspring.modelos.UsuarioFormModel;

import jakarta.validation.Valid;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Usuario aEntidad(UsuarioRegistrarEntradaDto dto);

    UsuarioRegistrarSalidaDto aDtoRegistro(Usuario usuario);

    UsuarioAutenticarSalidaDto aDtoAutenticacion(Usuario usuario);

	@Valid
	UsuarioRegistrarEntradaDto aDtoRegistro(@Valid UsuarioFormModel usuario);
}