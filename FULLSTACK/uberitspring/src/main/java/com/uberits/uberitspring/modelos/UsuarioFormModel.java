package com.uberits.uberitspring.modelos;

import jakarta.validation.constraints.Email;
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
public class UsuarioFormModel {
	@NotBlank
	@Size(max = 20)
	String nombre;
	@NotBlank
	@Size(max = 100)
	@Email
	String email;
	@NotBlank
	@Size(max = 100)
	String password;
}
