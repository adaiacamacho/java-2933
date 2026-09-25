package com.ipartex.accesodatos.jpa;

import java.util.Optional;

import com.ipartex.accesodatos.DaoUsuario;
import com.ipartex.entidades.Usuario;

import bibliotecas.accesodatos.DaoJpa;

public class DaoUsuarioJpa extends DaoJpa<Usuario> implements DaoUsuario {

	public DaoUsuarioJpa() {
		super(Usuario.class);
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return jpa.ejecutarJpa(
				em -> Optional.ofNullable(em.createQuery("from Usuario u where u.email = :email", Usuario.class)
						.setParameter("email", email).getSingleResultOrNull()));
	}
}
