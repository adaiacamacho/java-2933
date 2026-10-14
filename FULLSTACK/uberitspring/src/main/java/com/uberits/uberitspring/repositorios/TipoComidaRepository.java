package com.uberits.uberitspring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.uberits.uberitspring.entidades.TipoComida;

@RepositoryRestResource(path = "tipos-comida", collectionResourceRel = "tiposComida")
public interface TipoComidaRepository extends JpaRepository<TipoComida, Long> {

}
