package com.ipartex.logicanegocio.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.accesodatos.DaoUsuario;
import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;
import com.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocioImpl.class.getName());

	private static final DaoMensaje DAO_MENSAJE = (DaoMensaje) ContenedorInyeccionDependencias.obtenerObjeto("dao.mensaje");
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) ContenedorInyeccionDependencias.obtenerObjeto("dao.usuario");
	
	@Override
	public Iterable<Mensaje> listarMensajes() {
		log.info("El usuario pide el listado de mensajes");
		
		return DAO_MENSAJE.obtenerParaPantalla();
	}

	@Override
	public Optional<Mensaje> buscarMensajePorId(Long id) {
		log.info("El usuario pide el mensaje " + id);

		return DAO_MENSAJE.obtenerPorId(id);
	}

	@Override
	public Iterable<Mensaje> buscarMensajesPorFechaMinima(LocalDateTime fechaMinima) {
		log.info("El usuario busca los mensajes desde " + fechaMinima);
		
		return DAO_MENSAJE.obtenerMensajesPorFechaMinima(fechaMinima);
	}

	@Override
	public Mensaje nuevoMensaje(Mensaje mensaje) {
		log.info("El usuario inserta el mensaje " + mensaje);
		
		mensaje.setFechaHora(LocalDateTime.now());
		
		return DAO_MENSAJE.insertar(mensaje);
	}

	@Override
	public Mensaje editarMensaje(Mensaje mensaje) {
		log.info("El usuario modifica el mensaje " + mensaje);

		return DAO_MENSAJE.modificar(mensaje);
	}

	@Override
	public void eliminarMensaje(Long id) {
		log.info("El usuario borra el mensaje " + id);

		DAO_MENSAJE.borrar(id);
	}

	@Override
	public Optional<Usuario> autenticarUsuario(String email, String password) {
		log.info("El usuario intenta autenticarse");

		Optional<Usuario> usuario = DAO_USUARIO.obtenerPorEmail(email);
		
		if(usuario.isEmpty()) {
			return Optional.empty();
		}
		
		if(!usuario.get().getPassword().equals(password)) {
			return Optional.empty();
		}
		
		return usuario;
	}

}
