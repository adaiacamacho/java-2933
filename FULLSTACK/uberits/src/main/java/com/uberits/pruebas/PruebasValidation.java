package com.uberits.pruebas;

import java.util.Set;

import com.uberits.entidades.Usuario;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class PruebasValidation {
	public static void main(String[] args) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Javier");
		usuario.setEmail("a@b");
		usuario.setPassword("ñaslkdñglasd");
		
		Set<ConstraintViolation<Usuario>> validacion = validator.validate(usuario);
		
//		System.out.println(validacion);
		
		for(ConstraintViolation<Usuario> constraintViolation: validacion) {
//			System.out.println(constraintViolation);
			
			System.out.printf("%s %s\n", constraintViolation.getPropertyPath(), constraintViolation.getMessage());
		}
	}
}
