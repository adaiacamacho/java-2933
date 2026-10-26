package com.uberits.uberitspring.dtos;

import java.math.BigDecimal;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlatoDetalleDto(@NotNull Long id, @NotBlank @Size(max = 20) String nombre,
		@NotNull @Min(0) BigDecimal precio, @Size(max = 2000) @Lob String descripcion) {

}
