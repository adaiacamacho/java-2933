package com.uberits.accesodatos.jpa;

import com.uberits.accesodatos.DaoPlato;
import com.uberits.entidades.Plato;

import bibliotecas.accesodatos.DaoJpa;

public class DaoPlatoJpa extends DaoJpa<Plato> implements DaoPlato {

	public DaoPlatoJpa() {
		super(Plato.class);
	}
}
