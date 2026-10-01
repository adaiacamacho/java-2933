package com.uberits.logicanegocio;

import java.util.Optional;

import com.uberits.entidades.Restaurante;
import com.uberits.entidades.Usuario;

public interface AdministradorNegocio {
	Restaurante crearRestaurante(Restaurante restaurante);

	Optional<Usuario> obtenerPorId(Long id);
}
