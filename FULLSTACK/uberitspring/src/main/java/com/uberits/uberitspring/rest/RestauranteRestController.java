package com.uberits.uberitspring.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.uberits.uberitspring.dtos.RestauranteDetalleDto;
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

    @GetMapping("{id}")
    public RestauranteDetalleDto obtenerPorId(@PathVariable Long id) {
        return anonimoService.verRestaurante(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
