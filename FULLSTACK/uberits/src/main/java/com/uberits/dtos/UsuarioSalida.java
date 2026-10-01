package com.uberits.dtos;

import jakarta.validation.constraints.*;

public record UsuarioSalida(
	    Long id,

	    @NotBlank
	    @Size(max = 20)
	    String nombre,

	    @NotBlank
	    @Size(max = 100)
	    @Email
	    String email,
	    
	    Long idCliente) {

}
