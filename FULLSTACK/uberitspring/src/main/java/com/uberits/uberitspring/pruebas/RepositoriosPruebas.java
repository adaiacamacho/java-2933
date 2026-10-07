package com.uberits.uberitspring.pruebas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uberits.uberitspring.entidades.Usuario;
import com.uberits.uberitspring.repositorios.UsuarioRepository;

@Component
public class RepositoriosPruebas implements CommandLineRunner {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("INICIO");

		var javier = Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").build();
		var pepe = Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").build();
		
		usuarioRepository.saveAll(List.of(javier, pepe));
		
		for(var usuario: usuarioRepository.findAll()) {
			System.out.println(usuario);
		}
		
		System.out.println(usuarioRepository.findByEmail("javier@email.net"));
	}

}
