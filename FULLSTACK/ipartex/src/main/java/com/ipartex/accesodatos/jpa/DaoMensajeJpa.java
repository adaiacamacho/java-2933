package com.ipartex.accesodatos.jpa;

import java.util.List;
import java.util.Optional;

import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.AccesoDatosException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoMensajeJpa implements DaoMensaje {
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("com.ipartex.entidades");

	@Override
	public Iterable<Mensaje> obtenerTodos() {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			List<Mensaje> mensajes = em.createQuery("from Mensaje", Mensaje.class).getResultList();

			t.commit();

			return mensajes;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

	@Override
	public Optional<Mensaje> obtenerPorId(Long id) {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			Optional<Mensaje> mensaje = Optional.ofNullable(em.find(Mensaje.class, id));

			t.commit();

			return mensaje;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

	@Override
	public Iterable<Mensaje> obtenerParaPantalla() {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			List<Mensaje> mensajes = em.createQuery("from Mensaje m order by m.fechaHora desc", Mensaje.class)
					.getResultList();

			t.commit();

			return mensajes;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

	@Override
	public Mensaje insertar(Mensaje mensaje) {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			em.persist(mensaje);

			t.commit();

			return mensaje;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

	@Override
	public Mensaje modificar(Mensaje mensaje) {
		EntityTransaction t = null;

		try (EntityManager em = EMF.createEntityManager()) {
			t = em.getTransaction();

			t.begin();

			em.merge(mensaje);

			t.commit();

			return mensaje;
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

			em.remove(em.find(Mensaje.class, id));

			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new AccesoDatosException("Error en la operación de persistencia", e);
		}
	}

}
