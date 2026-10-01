package com.uberits.pruebas;

import static com.uberits.config.ContenedorDependencias.ANONIMO_NEGOCIO;

import com.uberits.dtos.UsuarioEntrada;
import com.uberits.entidades.Usuario;

public class PruebaAnonimoNegocio {
	public static void main(String[] args) {
//		System.out.println(ANONIMO_NEGOCIO.registrarse(new Usuario()));
		System.out.println(ANONIMO_NEGOCIO.registrarse(new Usuario(null, "Javier", "javier@email.net", "javier", null)));
		System.out.println(ANONIMO_NEGOCIO.registrarse(new Usuario(null, "Pepe", "pepe@email.net", "pepe", null)));

		System.out.println(ANONIMO_NEGOCIO.autenticarse(new UsuarioEntrada("javier@email.net", "javier")));

		System.out.println(ANONIMO_NEGOCIO.autenticarse(new UsuarioEntrada( "javier@email.net", "javie")));
	}
}
