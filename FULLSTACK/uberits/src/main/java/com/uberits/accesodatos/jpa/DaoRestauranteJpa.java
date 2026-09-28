package com.uberits.accesodatos.jpa;

import com.uberits.accesodatos.DaoRestaurante;
import com.uberits.entidades.Restaurante;

import bibliotecas.accesodatos.DaoJpa;

public class DaoRestauranteJpa extends DaoJpa<Restaurante> implements DaoRestaurante {

	public DaoRestauranteJpa() {
		super(Restaurante.class);
	}
}
