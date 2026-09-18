package com.ipartex.logicanegocio.impl;

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

}
