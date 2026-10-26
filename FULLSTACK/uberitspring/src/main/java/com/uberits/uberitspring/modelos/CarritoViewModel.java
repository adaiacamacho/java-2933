package com.uberits.uberitspring.modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Component
@SessionScope
public class CarritoViewModel {
	@Builder.Default
	private List<LineaViewModel> lineas = new ArrayList<>(); 
	
	public BigDecimal getTotal() {
		return lineas.stream().map(LineaViewModel::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder	
	public static class LineaViewModel {
		@NotNull
		private Long idPlato;
		
		@NotBlank
		private String nombrePlato;
		
		@NotNull
		@Positive
		private BigDecimal precioPlato;
		
		@NotNull
		@Positive
		@Builder.Default
		private Integer cantidad = 1;
		
		public BigDecimal getTotal() {
			return precioPlato.multiply(new BigDecimal(cantidad));
		}
	}
}
