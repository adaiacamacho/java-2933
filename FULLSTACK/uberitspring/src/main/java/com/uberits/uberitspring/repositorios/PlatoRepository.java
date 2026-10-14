package com.uberits.uberitspring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uberits.uberitspring.entidades.Plato;

@RepositoryRestResource(path = "platos", collectionResourceRel = "platos")
public interface PlatoRepository extends JpaRepository<Plato, Long> {
	
}
