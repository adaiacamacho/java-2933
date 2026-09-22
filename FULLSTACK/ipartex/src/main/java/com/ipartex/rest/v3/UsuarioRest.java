package com.ipartex.rest.v3;

import java.util.Optional;

import com.ipartex.entidades.Usuario;
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

@Path("/usuarios")
public class UsuarioRest {
	private static final AnonimoNegocio NEGOCIO = (AnonimoNegocio) ContenedorInyeccionDependencias
			.obtenerObjeto("negocio.anonimo");
	
	
	@GET
	public Iterable<Usuario> getUsuarios() {
		return NEGOCIO.listarUsuarios();
	}
	
	@GET
	@Path("{id}")
	public Usuario getUsuarioPorId(@PathParam("id") Long id) {
		Optional<Usuario> user = NEGOCIO.buscarUsuarioPorId(id);

		if (user.isEmpty()) {
			throw new NotFoundException("Usuario con id=" + id + " no encontrado");
		}

		return user.get();
	}

	/**
	 * Método POST para guardar un mensaje nuevo
	 * 
	 * @param mensaje mensaje a añadir
	 * @return mensaje añadido
	 */
	@POST
	public Response crearUsuario(Usuario user) {
		return Response.created(null).entity(NEGOCIO.nuevoUsuario(user)).build();
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
	public Usuario actualizarUsuario(@PathParam("id") Long id, Usuario user) {
		if (id != user.getId()) {
			throw new BadRequestException();
		}

		return NEGOCIO.editarUsuario(user);
	}

	/**
	 * Método DELETE para borrar un mensaje existente
	 * 
	 * @param id el id a borrar
	 */
	@DELETE
	@Path("{id}")
	public Response borrarUsuario(@PathParam("id") Long id) {
		NEGOCIO.eliminarUsuario(id);
		
		return Response.noContent().build();
	}
}
