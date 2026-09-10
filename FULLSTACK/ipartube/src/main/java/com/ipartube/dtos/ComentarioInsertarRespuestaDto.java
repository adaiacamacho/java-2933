package com.ipartube.dtos;

import java.time.LocalDateTime;

public record ComentarioInsertarRespuestaDto(Long id, LocalDateTime fechaHora, Long idUsuario, String texto, Long idVideo) {

}
