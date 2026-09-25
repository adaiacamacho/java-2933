package com.ipartex.accesodatos.jpa;

import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.DaoJpa;

public class DaoMensajeJpa extends DaoJpa<Mensaje> implements DaoMensaje {

	public DaoMensajeJpa() {
		super(Mensaje.class);
	}

	@Override
	public Iterable<Mensaje> obtenerParaPantalla() {
		return jpa.ejecutarJpa(em -> em.createQuery("from Mensaje m order by m.fechaHora desc", Mensaje.class).getResultList());
	}
}
