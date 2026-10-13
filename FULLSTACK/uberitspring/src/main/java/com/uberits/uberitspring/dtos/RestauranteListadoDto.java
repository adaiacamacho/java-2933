package com.uberits.uberitspring.dtos;

import lombok.Builder;

@Builder
public record RestauranteListadoDto(String nombre, Iterable<String> tiposComida) {

}
