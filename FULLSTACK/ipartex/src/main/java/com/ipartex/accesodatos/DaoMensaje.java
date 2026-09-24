package com.ipartex.accesodatos;

import com.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.AccesoDatosException;
import bibliotecas.accesodatos.Dao;

public interface DaoMensaje extends Dao<Mensaje> {
	default Iterable<Mensaje> obtenerParaPantalla() {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
}
