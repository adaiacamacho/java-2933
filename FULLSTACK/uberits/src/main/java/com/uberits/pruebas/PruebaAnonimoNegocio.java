package com.uberits.pruebas;

import static com.uberits.config.ContenedorDependencias.*;

import com.uberits.entidades.Usuario;

public class PruebaAnonimoNegocio {
	public static void main(String[] args) {
//		System.out.println(ANONIMO_NEGOCIO.registrarse(new Usuario()));
		System.out.println(ANONIMO_NEGOCIO
				.registrarse(Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").build()));
		System.out.println(ANONIMO_NEGOCIO
				.registrarse(Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").build()));

		System.out.println(
				ANONIMO_NEGOCIO.autenticarse(Usuario.builder().email("javier@email.net").password("javier").build()));

		System.out.println(
				ANONIMO_NEGOCIO.autenticarse(Usuario.builder().email("javier@email.net").password("javie").build()));
	}
}
