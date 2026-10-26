package com.uberits.uberitspring.dtos;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record RestauranteDetalleDto(String nombre, Iterable<String> tiposComida, Iterable<PlatoDto> platos) {

	@Builder
	public record PlatoDto(Long id, String nombre, BigDecimal precio, String descripcion) {}
}
