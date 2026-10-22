package com.uberits.uberitspring.dtos;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteSalidaDto {
	@Nullable
	private Long id;
	
	@NotBlank
	@Size(max = 30)
	private String nombre;

	@Builder.Default
	private List<Long> tiposComidaIds = new ArrayList<>();
}
