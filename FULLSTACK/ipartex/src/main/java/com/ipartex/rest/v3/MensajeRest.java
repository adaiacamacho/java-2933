package com.ipartex.rest.v3;

import java.time.LocalDateTime;
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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/mensajes")
public class MensajeRest {
	private static final AnonimoNegocio NEGOCIO = (AnonimoNegocio) ContenedorInyeccionDependencias
			.obtenerObjeto("negocio.anonimo");

	@GET
	public Iterable<Mensaje> getMensajes() {
		return NEGOCIO.listarMensajes();
	}

	@GET
	@Path("{id}")
	public Mensaje getMensajePorId(@PathParam("id") Long id) {
		Optional<Mensaje> mensaje = NEGOCIO.buscarMensajePorId(id);

		if (mensaje.isEmpty()) {
			throw new NotFoundException("Mensaje con id=" + id + " no encontrado");
		}

		return mensaje.get();
	}
	
	@GET
	@Path("buscar")
	public Iterable<Mensaje> buscarMensajes(@QueryParam("fecha-minima") String fechaMinima) {
		return NEGOCIO.buscarMensajesPorFechaMinima(LocalDateTime.parse(fechaMinima));
	}

	@POST
	public Response crearMensaje(Mensaje mensaje) {
		return Response.created(null).entity(NEGOCIO.nuevoMensaje(mensaje)).build();
	}

	@PUT
	@Path("{id}")
	public Mensaje actualizarMensaje(@PathParam("id") Long id, Mensaje mensaje) {
		if (id != mensaje.getId()) {
			throw new BadRequestException();
		}

		return NEGOCIO.editarMensaje(mensaje);
	}

	@DELETE
	@Path("{id}")
	public Response borrarMensaje(@PathParam("id") Long id) {
		NEGOCIO.eliminarMensaje(id);
		
		return Response.noContent().build();
	}
}
