package com.uberits.uberitspring.pruebas;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uberits.uberitspring.entidades.Plato;
import com.uberits.uberitspring.entidades.Restaurante;
import com.uberits.uberitspring.entidades.TipoComida;
import com.uberits.uberitspring.entidades.Usuario;
import com.uberits.uberitspring.repositorios.PlatoRepository;
import com.uberits.uberitspring.repositorios.RestauranteRepository;
import com.uberits.uberitspring.repositorios.TipoComidaRepository;
import com.uberits.uberitspring.repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Component
public class RepositoriosPruebas implements CommandLineRunner {
	private final UsuarioRepository usuarioRepository;
	private final RestauranteRepository restauranteRepository;
	private final TipoComidaRepository tipoComidaRepository;
	private final PlatoRepository platoRepository;

	@Override
	public void run(String... args) throws Exception {
		var javier = Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").build();
		var pepe = Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").build();
		
		usuarioRepository.saveAll(List.of(javier, pepe));
		
		for(var usuario: usuarioRepository.findAll()) {
			System.out.println(usuario);
		}
		
		System.out.println(usuarioRepository.findByEmail("javier@email.net"));
		
		var americana = TipoComida.builder().nombre("Americana").build();
		var italiana = TipoComida.builder().nombre("Italiana").build();
		
		tipoComidaRepository.saveAll(List.of(americana, italiana));
		
		var teleBurger = Restaurante.builder().nombre("Tele Burger").tiposComida(List.of(americana, italiana)).build();
		
		restauranteRepository.save(teleBurger);
		
		var pizza = Plato.builder().nombre("Pizza").precio(new BigDecimal("12.34")).restaurante(teleBurger).build();
		
		var burger = Plato.builder().nombre("Burger").precio(new BigDecimal("5.67")).restaurante(teleBurger).build();
		
		platoRepository.saveAll(List.of(pizza, burger));
	}

}
