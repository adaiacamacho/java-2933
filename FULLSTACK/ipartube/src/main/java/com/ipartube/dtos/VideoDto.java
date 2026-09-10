package com.ipartube.dtos;

import java.time.LocalDate;

public record VideoDto(Long id, LocalDate fecha, String url, String titulo, String descripcion) {

}
