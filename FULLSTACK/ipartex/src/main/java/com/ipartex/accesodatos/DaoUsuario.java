package com.ipartex.accesodatos;

import com.ipartex.entidades.Usuario;

import bibliotecas.accesodatos.AccesoDatosException;

public interface DaoUsuario extends Dao<Usuario> {
	default Iterable<Usuario> obtenerParaPantalla() {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
}
