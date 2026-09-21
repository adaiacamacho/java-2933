package com.ipartex.rest.v3;

import com.ipartex.entidades.Mensaje;
import com.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/mensajes")
public class MensajeRest {
	private static final AnonimoNegocio NEGOCIO = (AnonimoNegocio) ContenedorInyeccionDependencias.obtenerObjeto("negocio.anonimo");
	
	@GET
	public Iterable<Mensaje> getMensajes() {
		return NEGOCIO.listarMensajes();
	}
}
