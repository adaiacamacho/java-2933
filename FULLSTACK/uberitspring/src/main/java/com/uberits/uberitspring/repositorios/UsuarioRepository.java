package com.uberits.uberitspring.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.uberits.uberitspring.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
