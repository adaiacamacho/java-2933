package com.uberits.accesodatos.jpa;

import java.util.Optional;

import com.uberits.accesodatos.DaoUsuario;
import com.uberits.entidades.Usuario;

import bibliotecas.accesodatos.DaoJpa;

public class DaoUsuarioJpa extends DaoJpa<Usuario> implements DaoUsuario {

	public DaoUsuarioJpa() {
		super(Usuario.class);
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return JPA.ejecutarJpa(
				em -> Optional.ofNullable(em.createQuery("from Usuario u where u.email = :email", Usuario.class)
						.setParameter("email", email).getSingleResultOrNull()));
	}
}
