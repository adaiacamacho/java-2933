package com.ipartex.pruebas;

import java.time.LocalDateTime;

import com.ipartex.entidades.Mensaje;

public class MensajePruebas {
	public static void main(String[] args) {
		LocalDateTime ahora = LocalDateTime.now();
		
		Mensaje m1 = new Mensaje(1L, "Javier", "Hola a todos", ahora);
		Mensaje m2 = new Mensaje(1L, "Javier", "Hola a todos", ahora);
		
		System.out.println(m1);
		System.out.println(m2);
		
		System.out.println(m1 == m2);
		System.out.println(m1.equals(m2));
	}
}
