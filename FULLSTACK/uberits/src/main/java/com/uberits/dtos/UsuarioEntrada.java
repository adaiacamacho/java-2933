package com.uberits.dtos;

import jakarta.validation.constraints.*;

public record UsuarioEntrada(
		@NotBlank
	    @Size(max = 100)
	    @Email
		String email, 
		@NotBlank
	    @Size(max = 100)
		String password) {

}
