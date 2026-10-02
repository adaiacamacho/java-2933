package com.uberits.logicanegocio.impl;

import java.util.Optional;

import com.uberits.accesodatos.DaoUsuario;
import com.uberits.entidades.Usuario;
import com.uberits.logicanegocio.AnonimoNegocio;

import bibliotecas.validaciones.Validador;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private DaoUsuario daoUsuario;
	private Validador validador;

	public AnonimoNegocioImpl(DaoUsuario daoUsuario, Validador validador) {
		this.daoUsuario = daoUsuario;
		this.validador = validador;
	}

	@Override
	public Usuario registrarse(Usuario usuario) {
		validador.validar(usuario, Usuario.class);
		
		return daoUsuario.insertar(usuario);
	}

	@Override
	public Optional<Usuario> autenticarse(Usuario usuario) {
		Optional<Usuario> usuarioEmail = daoUsuario.buscarPorEmail(usuario.getEmail());

		if (usuarioEmail.isEmpty() || !usuarioEmail.get().getPassword().equals(usuario.getPassword())) {
			return Optional.empty();
		}

		return usuarioEmail;
	}

}
