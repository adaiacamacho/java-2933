package com.ipartube.dtos;

import java.time.LocalDate;

public record VideoInsertarRespuestaDto(Long id, LocalDate fecha, String url, String titulo, String descripcion, Long idUsuario) {

}
