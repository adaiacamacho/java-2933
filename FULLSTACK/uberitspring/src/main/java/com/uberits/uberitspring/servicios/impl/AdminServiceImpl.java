package com.uberits.uberitspring.servicios.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uberits.uberitspring.dtos.RestauranteEntradaDto;
import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.dtos.RestauranteSalidaDto;
import com.uberits.uberitspring.dtos.TipoComidaDto;
import com.uberits.uberitspring.entidades.TipoComida;
import com.uberits.uberitspring.mappers.RestauranteMapper;
import com.uberits.uberitspring.mappers.TipoComidaMapper;
import com.uberits.uberitspring.repositorios.RestauranteRepository;
import com.uberits.uberitspring.repositorios.TipoComidaRepository;
import com.uberits.uberitspring.servicios.AdminService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Validated

@Log
@RequiredArgsConstructor

@RolesAllowed("ADMINISTRADOR")
@Service
public class AdminServiceImpl implements AdminService {
	private final RestauranteRepository restauranteRepository;
	private final TipoComidaRepository tipoComidaRepository;

	private final RestauranteMapper restauranteMapper;
	private final TipoComidaMapper tipoComidaMapper;

	@Override
	public Collection<RestauranteListadoDto> listadoRestaurantes() {
		return restauranteMapper.aDto(restauranteRepository.findAll());
	}

	@Override
	public void borrarRestaurante(Long id) {
		log.info("Borrando restaurante " + id);

		restauranteRepository.deleteById(id);
	}

	@Override
	public Iterable<TipoComidaDto> listadoTiposComida() {
		var tiposComida = tipoComidaRepository.findAll();

		return tipoComidaMapper.aDto(tiposComida);
	}

	@Override
	public RestauranteSalidaDto crearRestaurante(RestauranteEntradaDto restauranteDto) {
		var restauranteACrear = restauranteMapper.aEntidad(restauranteDto);
		
		List<Long> ids = restauranteDto.getTiposComidaIds() == null
                ? List.of()
                : restauranteDto.getTiposComidaIds().stream()
                    .distinct()
                    .toList();

        List<TipoComida> tipos = tipoComidaRepository.findAllById(ids);

        if (tipos.size() != ids.size()) {
            throw new EntityNotFoundException(
                "Uno o varios tipos de comida no existen"
            );
        }

        restauranteACrear.setTiposComida(new ArrayList<>(tipos));
				
		var restauranteCreado = restauranteRepository.save(restauranteACrear);
		
		return restauranteMapper.aDto(restauranteCreado);
	}

	@Override
	public RestauranteSalidaDto modificarRestaurante(RestauranteEntradaDto restauranteDto) {
		System.out.println(restauranteDto);
		var restauranteObj=restauranteMapper.aEntidad(restauranteDto);
		
		List<Long> ids = restauranteDto.getTiposComidaIds() == null
                ? List.of()
                : restauranteDto.getTiposComidaIds().stream()
                    .distinct()
                    .toList();

        List<TipoComida> tipos = tipoComidaRepository.findAllById(ids);

        if (tipos.size() != ids.size()) {
            throw new EntityNotFoundException(
                "Uno o varios tipos de comida no existen"
            );
        }
        
        restauranteObj.setTiposComida(tipos);
		
		restauranteRepository.save(restauranteObj);
		return restauranteMapper.aDto(restauranteObj);
	}
}
