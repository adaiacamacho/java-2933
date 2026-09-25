package com.ipartex.accesodatos;

import java.time.LocalDateTime;

import com.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.AccesoDatosException;
import bibliotecas.accesodatos.Dao;

public interface DaoMensaje extends Dao<Mensaje> {
	default Iterable<Mensaje> obtenerParaPantalla() {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	Iterable<Mensaje> obtenerMensajesPorFechaMinima(LocalDateTime fechaMinima);
}
