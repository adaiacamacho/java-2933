package com.uberits.uberitspring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.uberits.uberitspring.entidades.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
}
