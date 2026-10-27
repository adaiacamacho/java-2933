package com.uberits.uberitspring.config;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
public class JwtSecurityConfig {
	@Bean
	SecretKey jwtSecretKey(@Value("${seguridad.jwt.secreto}") String secreto) {
		System.out.println(secreto);
		
		byte[] clave = Base64.getDecoder().decode(secreto);
		return new SecretKeySpec(clave, "HmacSHA256");
	}

	@Bean
	JwtEncoder jwtEncoder(SecretKey clave) {
		return new NimbusJwtEncoder(new ImmutableSecret<>(clave));
	}

	@Bean
	JwtDecoder jwtDecoder(SecretKey clave) {
		return NimbusJwtDecoder.withSecretKey(clave).macAlgorithm(MacAlgorithm.HS256).build();
	}
}
