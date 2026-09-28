package com.uberits.accesodatos.jpa;

import com.uberits.accesodatos.DaoPedido;
import com.uberits.entidades.Pedido;

import bibliotecas.accesodatos.DaoJpa;

public class DaoPedidoJpa extends DaoJpa<Pedido> implements DaoPedido {

	public DaoPedidoJpa() {
		super(Pedido.class);
	}
}
