package com.uberits.uberitspring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uberits.uberitspring.entidades.Pedido.Linea;

@RepositoryRestResource(path = "pedido-lineas", collectionResourceRel = "pedidoLineas")
public interface PedidoLineaRepository extends JpaRepository<Linea, Long> {
	
}
