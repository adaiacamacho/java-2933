package com.ipartex.rest.v3;

import java.util.Optional;

import com.ipartex.entidades.Mensaje;
import com.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/mensajes")
public class MensajeRest {
	private static final AnonimoNegocio NEGOCIO = (AnonimoNegocio) ContenedorInyeccionDependencias
			.obtenerObjeto("negocio.anonimo");

	/**
	 * Método GET para obtener todos los mensajes
	 * 
	 * @return todos los mensajes
	 */
	@GET
	public Iterable<Mensaje> getMensajes() {
		return NEGOCIO.listarMensajes();
	}

	/**
	 * Método GET para obtener un mensaje según su ID
	 * 
	 * @param id id sobre el que se va a buscar un mensaje
	 * @return mensaje encontrado
	 */
	@GET
	@Path("{id}")
	public Mensaje getMensajePorId(@PathParam("id") Long id) {
		Optional<Mensaje> mensaje = NEGOCIO.buscarMensajePorId(id);

		if (mensaje.isEmpty()) {
			throw new NotFoundException("Mensaje con id=" + id + " no encontrado");
		}

		return mensaje.get();
	}

	/**
	 * Método POST para guardar un mensaje nuevo
	 * 
	 * @param mensaje mensaje a añadir
	 * @return mensaje añadido
	 */
	@POST
	public Response crearMensaje(Mensaje mensaje) {
		return Response.created(null).entity(NEGOCIO.nuevoMensaje(mensaje)).build();
	}

	/**
	 * Método PUT para modificar un mensaje existente
	 * 
	 * @param id      id destino
	 * @param mensaje mensaje a poner en destino
	 * @return mensaje
	 */
	@PUT
	@Path("{id}")
	public Mensaje actualizarMensaje(@PathParam("id") Long id, Mensaje mensaje) {
		if (id != mensaje.getId()) {
			throw new BadRequestException();
		}

		return NEGOCIO.editarMensaje(mensaje);
	}

	/**
	 * Método DELETE para borrar un mensaje existente
	 * 
	 * @param id el id a borrar
	 */
	@DELETE
	@Path("{id}")
	public Response borrarMensaje(@PathParam("id") Long id) {
		NEGOCIO.eliminarMensaje(id);
		
		return Response.noContent().build();
	}
}
