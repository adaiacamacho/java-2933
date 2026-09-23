package com.ipartex.rest.v3;

import java.util.Optional;

import com.ipartex.entidades.Usuario;
import com.ipartex.logicanegocio.AdministradorNegocio;

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

@Path("/usuarios")
public class UsuarioRest {
	private static final AdministradorNegocio NEGOCIO = (AdministradorNegocio) ContenedorInyeccionDependencias
			.obtenerObjeto("negocio.administrador");

	@GET
	public Iterable<Usuario> getUsuarios() {
		return NEGOCIO.obtenerListadoUsuarios();
	}

	@GET
	@Path("{id}")
	public Usuario getUsuarioPorId(@PathParam("id") Long id) {
		Optional<Usuario> usuario = NEGOCIO.obtenerUsuarioPorId(id);

		if (usuario.isEmpty()) {
			throw new NotFoundException("Usuario con id=" + id + " no encontrado");
		}

		return usuario.get();
	}
	
	@GET
	@Path("buscar/por-email")
	public Usuario getUsuarioPorEmail(@QueryParam("email") String email) {
		Optional<Usuario> usuario = NEGOCIO.obtenerUsuarioPorEmail(email);

		if (usuario.isEmpty()) {
			throw new NotFoundException("Usuario con email=" + email + " no encontrado");
		}

		return usuario.get();
	}

	@POST
	public Response crearUsuario(Usuario usuario) {
		return Response.created(null).entity(NEGOCIO.crearUsuario(usuario)).build();
	}

	@PUT
	@Path("{id}")
	public Usuario actualizarUsuario(@PathParam("id") Long id, Usuario usuario) {
		if (id != usuario.getId()) {
			throw new BadRequestException();
		}

		return NEGOCIO.modificarUsuario(usuario);
	}

	@DELETE
	@Path("{id}")
	public Response borrarMensaje(@PathParam("id") Long id) {
		NEGOCIO.borrarUsuario(id);
		
		return Response.noContent().build();
	}
}
