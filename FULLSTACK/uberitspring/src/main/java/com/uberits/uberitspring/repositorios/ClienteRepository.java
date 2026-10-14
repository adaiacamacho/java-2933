package com.uberits.uberitspring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uberits.uberitspring.entidades.Cliente;

@RepositoryRestResource(path = "clientes", collectionResourceRel = "clientes")
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
