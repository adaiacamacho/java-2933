package com.uberits.uberitspring.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.uberits.uberitspring.mappers.UsuarioMapper;
import com.uberits.uberitspring.repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log

@Component
public class UberitSpringUserDetailsService implements UserDetailsService {
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Usuario " + username + " logueando");

		var usuario = usuarioRepository.findByEmail(username);

		var uberitSpringUserDetails = usuarioMapper
				.aUserDetails(usuario.orElseThrow(() -> new UsernameNotFoundException(username)));

		uberitSpringUserDetails.roles()
				.add(uberitSpringUserDetails.getUsername().equals("javier@email.net") ? "ADMINISTRADOR" : "USUARIO");

		System.out.println(uberitSpringUserDetails);

		return uberitSpringUserDetails;
	}

}
