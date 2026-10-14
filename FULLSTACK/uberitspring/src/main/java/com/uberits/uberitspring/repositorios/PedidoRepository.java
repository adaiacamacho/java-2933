package com.uberits.uberitspring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uberits.uberitspring.entidades.Pedido;

@RepositoryRestResource(path = "pedidos", collectionResourceRel = "pedidos")
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
