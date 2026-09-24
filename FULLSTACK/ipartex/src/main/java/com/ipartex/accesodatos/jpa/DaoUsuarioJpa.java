package com.ipartex.accesodatos.jpa;

import java.util.Optional;

import com.ipartex.accesodatos.DaoUsuario;
import com.ipartex.entidades.Usuario;

import bibliotecas.accesodatos.JpaHelper;
import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class DaoUsuarioJpa implements DaoUsuario {
	private static final JpaHelper jpa = (JpaHelper) ContenedorInyeccionDependencias.obtenerObjeto("jpa.helper");

	@Override
	public Iterable<Usuario> obtenerTodos() {
		return jpa.ejecutarJpa(em -> em.createQuery("from Usuario", Usuario.class).getResultList());
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		return jpa.ejecutarJpa(em -> Optional.ofNullable(em.find(Usuario.class, id)));
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return jpa.ejecutarJpa(
				em -> Optional.ofNullable(em.createQuery("from Usuario u where u.email = :email", Usuario.class)
						.setParameter("email", email).getSingleResultOrNull()));
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		return jpa.ejecutarJpa(em -> {
			em.persist(usuario);
			return usuario;
		});
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		return jpa.ejecutarJpa(em -> {
			em.merge(usuario);
			return usuario;
		});
	}

	@Override
	public void borrar(Long id) {
		jpa.ejecutarJpa(em -> {
			em.remove(em.find(Usuario.class, id));
			return null;
		});
	}
}
