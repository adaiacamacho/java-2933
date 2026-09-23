package com.ipartex.logicanegocio;

import java.util.Optional;

import com.ipartex.entidades.Usuario;

public interface AdministradorNegocio {
	Iterable<Usuario> obtenerListadoUsuarios();

	Optional<Usuario> obtenerUsuarioPorId(Long id);

	Optional<Usuario> obtenerUsuarioPorEmail(String email);

	Usuario crearUsuario(Usuario usuario);

	Usuario modificarUsuario(Usuario usuario);

	void borrarUsuario(Long id);
}
