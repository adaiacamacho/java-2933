package com.ipartex.logicanegocio;

import java.util.Optional;

import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;

public interface AnonimoNegocio {
	Iterable<Mensaje> listarMensajes();
	Optional<Mensaje> buscarMensajePorId(Long id);

	Mensaje nuevoMensaje(Mensaje mensaje);
	Mensaje editarMensaje(Mensaje mensaje);
	void eliminarMensaje(Long id);
	
	Optional<Usuario> autenticarUsuario(String email, String password);
}
