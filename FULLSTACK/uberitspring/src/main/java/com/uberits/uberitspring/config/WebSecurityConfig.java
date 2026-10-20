package com.uberits.uberitspring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
	// AUTENTICACIÓN
	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		UserDetails javier = User.withUsername("javier@email.net").password("{noop}javier").roles("ADMINISTRADOR").build();
		UserDetails pepe = User.withUsername("pepe@email.net").password("{noop}pepe").roles("USUARIO").build();

		var jdbc = new JdbcUserDetailsManager(dataSource);

		jdbc.deleteUser(javier.getUsername());
		jdbc.deleteUser(pepe.getUsername());
		
		if (!jdbc.userExists(javier.getUsername())) {
			jdbc.createUser(javier);
		}

		if (!jdbc.userExists(pepe.getUsername())) {
			jdbc.createUser(pepe);
		}

		return jdbc; // new InMemoryUserDetailsManager(javier, pepe);
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