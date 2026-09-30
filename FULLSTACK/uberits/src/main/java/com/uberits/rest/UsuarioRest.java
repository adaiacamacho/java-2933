package com.uberits.rest;

import static com.uberits.config.ContenedorDependencias.*;

import java.util.Optional;

import com.uberits.entidades.Usuario;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/usuarios")
public class UsuarioRest {
	@POST
	public Usuario registrarUsuario(Usuario usuario) {
		return ANONIMO_NEGOCIO.registrarse(usuario);
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
