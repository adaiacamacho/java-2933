package com.uberits.uberitspring.pruebas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uberits.uberitspring.entidades.Usuario;
import com.uberits.uberitspring.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Component
public class ServiciosPruebas implements CommandLineRunner {
	private final AnonimoService anonimoService;

	@Override
	public void run(String... args) throws Exception {
//		var usuarioErroneo = Usuario.builder().build();
		
//		System.out.println(anonimoService.registrarse(usuarioErroneo));
		
		var juan = Usuario.builder().nombre("Juan").email("juan@email.net").password("javier").build();
		
		System.out.println(anonimoService.registrarse(juan));
	}

}
