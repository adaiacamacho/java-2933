package com.uberits.logicanegocio.impl;

import java.util.Optional;
import java.util.logging.Logger;

import com.uberits.accesodatos.DaoPedido;
import com.uberits.accesodatos.DaoRestaurante;
import com.uberits.entidades.Pedido;
import com.uberits.entidades.Restaurante;
import com.uberits.logicanegocio.UsuarioNegocio;

import bibliotecas.validaciones.Validador;

public class UsuarioNegocioImpl implements UsuarioNegocio{
	private static final Logger log = Logger.getLogger(UsuarioNegocio.class.getName());
	private DaoRestaurante daoRestaurante;
	private DaoPedido daoPedido;
	private Validador validador;

	
	public UsuarioNegocioImpl(DaoRestaurante daoRestaurante, DaoPedido daoPedido, Validador validador) {
		this.daoRestaurante = daoRestaurante;
		this.daoPedido = daoPedido;
		this.validador = validador;
	}

	@Override
	public Iterable<Restaurante> listadoRestaurantes() {
		log.info("Se han listado los restaurantes");
		return daoRestaurante.obtenerTodos();
	}

	@Override
	public Optional<Restaurante> verRestaurante(Long id) {
		log.info("Se ha obtenido un restaurante por id");
		return daoRestaurante.obtenerPorId(id);
	}

	@Override
	public Pedido crearPedido(Pedido pedido) {
		log.info("Se ha creado un pedido");
		validador.validar(pedido);
		return daoPedido.insertar(pedido);
	}

}
