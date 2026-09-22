package com.ipartex.rest.v3;

import java.time.LocalDateTime;

import com.ipartex.entidades.Mensaje;
import com.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;
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
	 * @return todos los mensajes
	 */
	@GET
	public Iterable<Mensaje> getMensajes() {
		return NEGOCIO.listarMensajes();
	}

	/**
	 *  Método GET para obtener un mensaje según su ID
	 * @param id id sobre el que se va a buscar un mensaje
	 * @return mensaje encontrado
	 */
	@GET
	@Path("{id}")
	public Mensaje getMensajePorId(@PathParam("id") Long id) {
		// No consultamos la capa de negocio: devolvemos un dato prefabricado
		if (id == 4) {
			// Lanzamos un 404
			throw new NotFoundException("Mensaje con id=" + id + " no encontrado");
		}
		
		return new Mensaje(id, "Usuario Ejemplo", "Mensaje de ejemplo para id=" + id, LocalDateTime.now());
	}

	/**
	 *  Método POST para guardar un mensaje nuevo
	 * @param mensaje mensaje a añadir
	 * @return mensaje añadido
	 */
	@POST
	public Response crearMensaje(Mensaje mensaje) {
		// Simular la creación: si el cliente no envía datos, se devuelven valores
		// prefabricados
		if (mensaje.getId() == null) {
			mensaje.setId(1000L); // id prefabricado
		}

		if (mensaje.getFechaHora() == null) {
			mensaje.setFechaHora(LocalDateTime.now());
		}

		return Response.created(null).entity(mensaje).build();
	}

	/**
	 *  Método PUT para modificar un mensaje existente
	 * @param id id destino
	 * @param mensaje mensaje a poner en destino
	 * @return mensaje
	 */
	@PUT
	@Path("{id}")
	public Mensaje actualizarMensaje(@PathParam("id") Long id, Mensaje mensaje) {
		// No se modifica realmente: devolvemos una representación actualizada
		mensaje.setId(id);

		return mensaje;
	}

	/**
	 * Método DELETE para borrar un mensaje existente
	 * @param id el id a borrar
	 */
	@DELETE
	@Path("{id}")
	public Response borrarMensaje(@PathParam("id") Long id) {
		// No se borra realmente: devolvemos un código de respuesta 204 (No Content)
		return Response.noContent().build();
	}
}
