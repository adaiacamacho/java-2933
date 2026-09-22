package com.ipartex.logicanegocio;

import java.util.Optional;

import com.ipartex.entidades.Mensaje;

public interface AnonimoNegocio {
	Iterable<Mensaje> listarMensajes();
	Optional<Mensaje> buscarMensajePorId(Long id);

	Mensaje nuevoMensaje(Mensaje mensaje);
	Mensaje editarMensaje(Mensaje mensaje);
	void eliminarMensaje(Long id);
}
