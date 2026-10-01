package com.uberits.rest;

import static com.uberits.config.ContenedorDependencias.*;

import java.net.URI;
import java.util.Optional;

import com.uberits.entidades.Usuario;

import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/usuarios")
public class UsuarioRest {
	@GET
	@Path("{id}")
	public Usuario getUsuario(@PathParam("id") Long id) {
		// TODO: Usar lógica de negocio de administrador y NO saltar directamente al DAO
		Optional<Usuario> usuario = DAO_USUARIO.obtenerPorId(id);

		if (usuario.isEmpty()) {
			throw new NotFoundException();
		}
		
		return usuario.get();
	}

	@POST
	public Response registrarUsuario(@Valid Usuario usuario, @Context UriInfo uriInfo) {
		Usuario usuarioRegistrado = ANONIMO_NEGOCIO.registrarse(usuario);

		URI location = uriInfo.getAbsolutePathBuilder().path(usuarioRegistrado.getId().toString()).build();

		return Response.created(location).entity(usuarioRegistrado).build();
	}

	@POST
	@Path("autenticacion")
	public Usuario autenticarse(Usuario usuario) {
		Optional<Usuario> usuarioAutenticado = ANONIMO_NEGOCIO.autenticarse(usuario);

		if (usuarioAutenticado.isEmpty()) {
			throw new NotAuthorizedException("Credenciales incorrectas");
		}

		return usuarioAutenticado.get();
	}
}
