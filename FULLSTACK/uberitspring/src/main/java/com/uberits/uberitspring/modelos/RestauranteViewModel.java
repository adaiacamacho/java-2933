package com.uberits.uberitspring.modelos;

import lombok.Builder;

@Builder
public record RestauranteViewModel(Long id, String nombre, Iterable<String> tiposComida) {

}
