package com.uberits.uberitspring.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TipoComidaDto(@NotNull Long id, @NotBlank String nombre) {

}
