package com.uberits.rest;

import org.glassfish.jersey.server.ResourceConfig;

import bibliotecas.rest.JacksonConfig;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api/v1")
public class AplicacionRest extends ResourceConfig {

    public AplicacionRest() {
    	packages("com.uberits", "bibliotecas.validaciones.rest");
        
    	register(JacksonConfig.class);
        register(org.glassfish.jersey.server.validation.ValidationFeature.class);
    }
}