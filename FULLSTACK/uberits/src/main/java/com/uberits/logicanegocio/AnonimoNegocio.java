package com.uberits.logicanegocio;

import java.util.Optional;

import com.uberits.dtos.UsuarioEntrada;
import com.uberits.dtos.UsuarioSalida;
import com.uberits.entidades.Usuario;

public interface AnonimoNegocio {
	Usuario registrarse(Usuario usuario);
	Optional<UsuarioSalida> autenticarse(UsuarioEntrada usuario);
}
