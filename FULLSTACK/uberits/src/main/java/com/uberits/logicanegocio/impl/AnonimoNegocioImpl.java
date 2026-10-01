package com.uberits.logicanegocio.impl;

import java.util.Optional;

import com.uberits.accesodatos.DaoUsuario;
import com.uberits.dtos.UsuarioEntrada;
import com.uberits.dtos.UsuarioSalida;
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
		validador.validar(usuario);
		
		return daoUsuario.insertar(usuario);
	}

	@Override
	public Optional<UsuarioSalida> autenticarse(UsuarioEntrada usuario) {
		validador.validar(usuario);
		Optional<Usuario> usuarioEmail = daoUsuario.buscarPorEmail(usuario.email());

		if (usuarioEmail.isEmpty() || !usuarioEmail.get().getPassword().equals(usuario.password())) {
			return Optional.empty();
		}
		Usuario userDatos=usuarioEmail.get();
		UsuarioSalida usuarioSalida=new UsuarioSalida(userDatos.getId(), userDatos.getNombre(), userDatos.getEmail(), userDatos.getCliente().getId());
		return Optional.ofNullable(usuarioSalida);
	}

}
