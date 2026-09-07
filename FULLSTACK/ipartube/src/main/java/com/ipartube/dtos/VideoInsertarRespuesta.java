package com.ipartube.dtos;

import java.time.LocalDate;

public record VideoInsertarRespuesta(Long id, LocalDate fecha, String url, String titulo, String descripcion, Long idUsuario) {

}
