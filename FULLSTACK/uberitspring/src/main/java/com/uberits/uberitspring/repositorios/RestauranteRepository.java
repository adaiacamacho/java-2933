package com.uberits.uberitspring.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uberits.uberitspring.entidades.Restaurante;

@RepositoryRestResource(path = "restaurantes", collectionResourceRel = "restaurantes")
public interface RestauranteRepository extends CrudRepository<Restaurante, Long> {
}
