package com.uberits.rest;

import static com.uberits.config.ContenedorDependencias.*;

import java.util.Optional;

import com.uberits.entidades.Pedido;
import com.uberits.entidades.Restaurante;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/restaurantes")
public class RestauranteRest {
	
	@GET
	public Iterable<Restaurante> listadoRestaurantes(){
		return USUARIO_NEGOCIO.listadoRestaurantes();
	}
	
	@GET
	@Path("{id}")
	public Optional<Restaurante> verRestaurante(@PathParam("id") Long id) {
		return USUARIO_NEGOCIO.verRestaurante(id);
	}
	
	@POST
	public Pedido crearPedido(Pedido pedido) {
		return USUARIO_NEGOCIO.crearPedido(pedido);
	}
	
	@POST
	public Restaurante crearRestaurante(Restaurante restaurante) {
		return ADMINISTRADOR_NEGOCIO.crearRestaurante(restaurante);
	}
}
	