package com.uberits.uberitspring.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uberits.uberitspring.entidades.TipoComida;

@RepositoryRestResource(path = "tipos-comida", collectionResourceRel = "tiposComida")
public interface TipoComidaRepository extends CrudRepository<TipoComida, Long> {

}
