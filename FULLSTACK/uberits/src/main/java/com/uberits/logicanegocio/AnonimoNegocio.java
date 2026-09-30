package com.uberits.logicanegocio;

import java.util.Optional;

import com.uberits.entidades.Usuario;

public interface AnonimoNegocio {
	Usuario registrarse(Usuario usuario);
	Optional<Usuario> autenticarse(Usuario usuario);
}
