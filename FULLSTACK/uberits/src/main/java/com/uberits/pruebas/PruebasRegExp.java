package com.uberits.pruebas;

public class PruebasRegExp {
	public static void main(String[] args) {
		String numeroFactura = "2026-0001";
		String numeroTelefono = "654123123";
		String codigoPostal = "12345";
		String email = "a@b.c";
		
		System.out.println(numeroFactura.matches("^\\d{4}-\\d{4}$"));
		System.out.println(numeroTelefono.matches("^\\d{9}$"));
		System.out.println(codigoPostal.matches("^\\d{5}$"));
		System.out.println(email.matches("^\\w+@\\w+\\.\\w+$"));		
	}
}
