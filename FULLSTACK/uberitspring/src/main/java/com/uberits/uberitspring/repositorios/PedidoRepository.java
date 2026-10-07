package com.uberits.uberitspring.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uberits.uberitspring.entidades.Pedido;

@RepositoryRestResource(path = "pedidos", collectionResourceRel = "pedidos")
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
}
