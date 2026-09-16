package com.ipartex.pruebas;

import java.util.function.BinaryOperator;

public class LambdaPruebas {
	public static void main(String[] args) {
		Operacion op = new Sumar();
		
		System.out.println(op.operar(1, 2));
		
		op = new Restar(); 

		System.out.println(op.operar(5, 3));
		
		op = new Operacion() {
			@Override
			public int operar(int a, int b) {
				return a * b;
			}
		};
		
		System.out.println(op.operar(5, 3));
		
		op = (a, b) -> a / b;
		
		System.out.println(op.operar(6, 3));
		
		BinaryOperator<Double> operacion = (a, b) -> Math.pow(a, b);
		
		System.out.println(operacion.apply(5.0, 3.0));
	}
	
	private static class Restar implements Operacion {
		@Override
		public int operar(int a, int b) {
			return a - b;
		}
	}
}

interface Operacion {
	int operar(int a, int b);
}

class Sumar implements Operacion {
	@Override
	public int operar(int a, int b) {
		return a + b;
	}
}