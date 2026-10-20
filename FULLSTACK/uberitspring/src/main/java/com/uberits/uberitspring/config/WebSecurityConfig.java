package com.uberits.uberitspring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
	// AUTENTICACIÓN
	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		var jdbc = new JdbcUserDetailsManager(dataSource);

		jdbc.setUsersByUsernameQuery("""
				SELECT email,password,'true' AS enabled 
				FROM usuarios 
				WHERE email = ?
				""");
		jdbc.setAuthoritiesByUsernameQuery("""
				SELECT email, CASE
				  WHEN email = 'javier@email.net' THEN 'ROLE_ADMINISTRADOR'
				  ELSE 'ROLE_USUARIO'
				END As authority FROM uberitspring.usuarios
				WHERE email = ?
				""");
		
		return jdbc;
	}

	// AUTORIZACIÓN
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		// @formatter:off
		http
			.authorizeHttpRequests(requests -> requests
				.requestMatchers("/", "/registro").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout(LogoutConfigurer::permitAll);
		// @formatter:on

		return http.build();
	}

}