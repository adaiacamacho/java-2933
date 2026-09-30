package com.uberits.accesodatos.jpa;

import com.uberits.accesodatos.DaoTipoComida;
import com.uberits.entidades.TipoComida;

import bibliotecas.accesodatos.DaoJpa;

public class DaoTipoComidaJpa extends DaoJpa<TipoComida> implements DaoTipoComida {

	public DaoTipoComidaJpa() {
		super(TipoComida.class);
	}
}
