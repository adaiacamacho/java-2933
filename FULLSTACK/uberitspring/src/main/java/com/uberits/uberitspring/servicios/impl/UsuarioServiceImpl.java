package com.uberits.uberitspring.servicios.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uberits.uberitspring.dtos.PlatoDetalleDto;
import com.uberits.uberitspring.mappers.PlatoMapper;
import com.uberits.uberitspring.repositorios.PlatoRepository;
import com.uberits.uberitspring.servicios.UsuarioService;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RolesAllowed("USUARIO")
@Service
public class UsuarioServiceImpl implements UsuarioService {
	private final PlatoRepository platoRepository;
	private final PlatoMapper platoMapper;
	
	@Override
	public Optional<PlatoDetalleDto> verPlato(Long idPlato) {
		return platoMapper.aDtoDetalle(platoRepository.findById(idPlato));
	}

}
