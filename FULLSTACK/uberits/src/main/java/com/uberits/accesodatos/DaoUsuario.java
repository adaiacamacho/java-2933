package com.uberits.accesodatos;

import java.util.Optional;

import com.uberits.entidades.Usuario;

import bibliotecas.accesodatos.Dao;

public interface DaoUsuario extends Dao<Usuario> {
	Optional<Usuario> buscarPorEmail(String email);
}
