package com.uberits.pruebas;

import com.uberits.entidades.Cliente;
import com.uberits.entidades.Usuario;

public class PruebasEquals {
	public static void main(String[] args) {
		var cliente1 = Cliente.builder().id(1L).nombre("Javier Lete").build();
		var cliente2 = Cliente.builder().id(1L).nombre("Javier Lete").build();
		
		var usuario1 = Usuario.builder().id(1L).nombre("Javier").cliente(cliente1).build();
		var usuario2 = Usuario.builder().id(2L).nombre("Javie").cliente(cliente2).build();
		
		cliente1.setUsuario(usuario1);
		cliente2.setUsuario(usuario2);
		
		System.out.println(usuario1);
		System.out.println(usuario2);
		
		System.out.println(usuario1.equals(usuario2));
	}
}
