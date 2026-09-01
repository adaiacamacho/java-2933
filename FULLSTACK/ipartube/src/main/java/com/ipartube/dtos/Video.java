package com.ipartube.dtos;

import java.time.LocalDate;

public record Video(Long id, LocalDate fecha, String url, String titulo, String descripcion) {

}
