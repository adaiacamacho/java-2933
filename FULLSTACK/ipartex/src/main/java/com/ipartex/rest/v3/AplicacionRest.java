package com.ipartex.rest.v3;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api/v3")
public class AplicacionRest extends ResourceConfig {

    public AplicacionRest() {
        packages("com.ipartex"); // escanea este paquete y subpaquetes
        register(JacksonConfig.class);
    }
}