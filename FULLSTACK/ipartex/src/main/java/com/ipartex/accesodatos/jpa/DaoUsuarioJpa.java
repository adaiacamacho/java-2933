package com.ipartex.accesodatos.jpa;

import java.util.List;
import java.util.Optional;

import com.ipartex.accesodatos.DaoUsuario;
import com.ipartex.entidades.Usuario;

import bibliotecas.accesodatos.AccesoDatosException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoUsuarioJpa implements DaoUsuario {
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("com.ipartex.entidades");

	@Override
	public Iterable<Usuario> obtenerTodos() {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();

			t.commit();

			return usuarios;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			Optional<Usuario> usuario = Optional.ofNullable(em.find(Usuario.class, id));

			t.commit();

			return usuario;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			em.persist(usuario);

			t.commit();

			return usuario;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			em.merge(usuario);

			t.commit();

			return usuario;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

	@Override
	public void borrar(Long id) {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			em.remove(em.find(Usuario.class, id));

			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

}
