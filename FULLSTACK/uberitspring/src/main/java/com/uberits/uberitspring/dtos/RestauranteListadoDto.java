package com.uberits.uberitspring.dtos;

import lombok.Builder;

@Builder
public record RestauranteListadoDto(Long id, String nombre, Iterable<String> tiposComida) {

}
