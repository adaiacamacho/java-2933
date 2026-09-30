package com.uberits.accesodatos.jpa;

import com.uberits.accesodatos.DaoCliente;
import com.uberits.entidades.Cliente;

import bibliotecas.accesodatos.DaoJpa;

public class DaoClienteJpa extends DaoJpa<Cliente> implements DaoCliente {

	public DaoClienteJpa() {
		super(Cliente.class);
	}
}
