package com.uberits.uberitspring.servicios;

import com.uberits.uberitspring.config.UberitSpringUserDetails;

public interface JwtService {

	String generarToken(UberitSpringUserDetails usuario);

}