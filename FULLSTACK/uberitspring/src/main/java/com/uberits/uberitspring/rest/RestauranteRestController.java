package com.uberits.uberitspring.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberits.uberitspring.dtos.RestauranteListadoDto;
import com.uberits.uberitspring.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 

@RestController
@RequestMapping("/api/v2/restaurantes")
public class RestauranteRestController {
    private final AnonimoService anonimoService;

    @GetMapping
    public Iterable<RestauranteListadoDto> obtenerTodos() {
        return anonimoService.listadoRestaurantes();
    }
    
}
