package com.uberits.uberitspring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.uberits.uberitspring.entidades.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
}
