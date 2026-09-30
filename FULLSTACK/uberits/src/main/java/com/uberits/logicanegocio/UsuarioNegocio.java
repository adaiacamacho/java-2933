package com.uberits.logicanegocio;

import java.util.Optional;

import com.uberits.entidades.Pedido;
import com.uberits.entidades.Restaurante;

public interface UsuarioNegocio {
	Iterable<Restaurante> listadoRestaurantes();
	Optional<Restaurante> verRestaurante(Long id);
	Pedido crearPedido(Pedido pedido);
}
