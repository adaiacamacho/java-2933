package com.uberits.logicanegocio.impl;

import java.util.Optional;

import com.uberits.accesodatos.DaoUsuario;
import com.uberits.entidades.Usuario;
import com.uberits.logicanegocio.AnonimoNegocio;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private DaoUsuario daoUsuario;

	public AnonimoNegocioImpl(DaoUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	@Override
	public Usuario registrarse(Usuario usuario) {
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
