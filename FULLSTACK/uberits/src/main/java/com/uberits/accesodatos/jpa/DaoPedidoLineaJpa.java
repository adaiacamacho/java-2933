package com.uberits.accesodatos.jpa;

import com.uberits.accesodatos.DaoPedidoLinea;
import com.uberits.entidades.Pedido.Linea;

import bibliotecas.accesodatos.DaoJpa;

public class DaoPedidoLineaJpa extends DaoJpa<Linea> implements DaoPedidoLinea {

	public DaoPedidoLineaJpa() {
		super(Linea.class);
	}
}
