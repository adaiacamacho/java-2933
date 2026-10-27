package com.uberits.uberitspring.servicios.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.uberits.uberitspring.servicios.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

	private final JwtEncoder jwtEncoder;
	private final long caducidadEnSegundos;

	public JwtServiceImpl(JwtEncoder jwtEncoder, @Value("${seguridad.jwt.caducidad}") long caducidadEnSegundos) {
		this.jwtEncoder = jwtEncoder;
		this.caducidadEnSegundos = caducidadEnSegundos;
	}

	@Override
	public String generarToken(String username) {
		Instant now = Instant.now();

		// @formatter:off
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("ubertiSpring")
				.issuedAt(now)
				.expiresAt(now.plusSeconds(caducidadEnSegundos))
				.subject(username)
				.build();

		JwsHeader header = JwsHeader
				.with(MacAlgorithm.HS256)
				.build();
		// @formatter:on

		return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
	}
}