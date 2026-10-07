package com.uberits.uberitspring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.uberits.uberitspring.entidades.Restaurante;

public interface RestauranteRepository extends CrudRepository<Restaurante, Long> {
}
