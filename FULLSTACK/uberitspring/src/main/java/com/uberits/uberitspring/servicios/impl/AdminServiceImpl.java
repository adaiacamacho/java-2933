package com.uberits.uberitspring.servicios.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.mappers.RestauranteMapper;
import com.uberits.uberitspring.repositorios.RestauranteRepository;
import com.uberits.uberitspring.servicios.AdminService;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Validated

@Log
@RequiredArgsConstructor

@RolesAllowed("ADMINISTRADOR")
@Service
public class AdminServiceImpl implements AdminService {
	private final RestauranteRepository restauranteRepository;
	private final RestauranteMapper restauranteMapper;

	@Override
	public Collection<RestauranteListadoDto> listadoRestaurantes() {
		return restauranteMapper.aDto(restauranteRepository.findAll());
	}
}
