package com.uberits.rest;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api/v1")
public class AplicacionRest extends ResourceConfig {

    public AplicacionRest() {
    	packages("com.uberits");
        register(JacksonConfig.class);
    }
}