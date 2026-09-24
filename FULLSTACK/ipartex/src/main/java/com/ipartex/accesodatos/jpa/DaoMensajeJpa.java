package com.ipartex.accesodatos.jpa;

import java.util.Optional;

import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.JpaHelper;
import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class DaoMensajeJpa implements DaoMensaje {
	private static final JpaHelper jpa = (JpaHelper) ContenedorInyeccionDependencias.obtenerObjeto("jpa.helper");

	@Override
	public Iterable<Mensaje> obtenerTodos() {
		return jpa.ejecutarJpa(em -> em.createQuery("from Mensaje", Mensaje.class).getResultList());
	}

	@Override
	public Optional<Mensaje> obtenerPorId(Long id) {
		return jpa.ejecutarJpa(em -> Optional.ofNullable(em.find(Mensaje.class, id)));
	}

	@Override
	public Iterable<Mensaje> obtenerParaPantalla() {
		return jpa.ejecutarJpa(em -> em.createQuery("from Mensaje m order by m.fechaHora desc", Mensaje.class).getResultList());
	}

	@Override
	public Mensaje insertar(Mensaje mensaje) {
		return jpa.ejecutarJpa(em -> {
			em.persist(mensaje);
			return mensaje;
		});
	}

	@Override
	public Mensaje modificar(Mensaje mensaje) {
		return jpa.ejecutarJpa(em -> {
			em.merge(mensaje);
			return mensaje;
		});
	}

	@Override
	public void borrar(Long id) {
		jpa.ejecutarJpa(em -> {
			em.remove(em.find(Mensaje.class, id));
			return null;
		});
	}
}
