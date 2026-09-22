package com.ipartex.logicanegocio.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.entidades.Mensaje;
import com.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocioImpl.class.getName());
	private static final DaoMensaje DAO = (DaoMensaje) ContenedorInyeccionDependencias.obtenerObjeto("dao.mensaje");
	
	@Override
	public Iterable<Mensaje> listarMensajes() {
		log.info("El usuario pide el listado de mensajes");
		
		return DAO.obtenerParaPantalla();
	}

	@Override
	public Optional<Mensaje> buscarMensajePorId(Long id) {
		log.info("El usuario pide el mensaje " + id);

		return DAO.obtenerPorId(id);
	}

	@Override
	public Mensaje nuevoMensaje(Mensaje mensaje) {
		log.info("El usuario inserta el mensaje " + mensaje);
		
		mensaje.setFechaHora(LocalDateTime.now());
		
		return DAO.insertar(mensaje);
	}

	@Override
	public Mensaje editarMensaje(Mensaje mensaje) {
		log.info("El usuario modifica el mensaje " + mensaje);

		return DAO.modificar(mensaje);
	}

	@Override
	public void eliminarMensaje(Long id) {
		log.info("El usuario borra el mensaje " + id);

		DAO.borrar(id);
	}

}
