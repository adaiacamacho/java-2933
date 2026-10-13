package com.uberits.uberitspring.modelos;

import lombok.Builder;

@Builder
public record RestauranteViewModel(String nombre, Iterable<String> tiposComida) {

}
