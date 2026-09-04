package com.ipartube.dtos;

import java.time.LocalDateTime;

public record ComentarioInsertarRespuesta(Long id, LocalDateTime fechaHora, Long idUsuario, String texto, Long idVideo) {

}
