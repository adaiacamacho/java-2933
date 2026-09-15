package com.ipartex.accesodatos;

import com.ipartex.entidades.Mensaje;

public interface DaoMensaje extends Dao<Mensaje> {
	default Iterable<Mensaje> obtenerTodosParaPantalla() {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
}
