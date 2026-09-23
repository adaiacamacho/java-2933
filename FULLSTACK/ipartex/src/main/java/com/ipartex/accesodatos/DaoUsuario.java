package com.ipartex.accesodatos;

import java.util.Optional;

import com.ipartex.entidades.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
	Optional<Usuario> obtenerPorEmail(String email);
}
