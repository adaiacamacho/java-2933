package com.ipartube.dtos;

import java.time.LocalDateTime;

public record ComentarioInsertarRespuesta(Long id, LocalDateTime fechaHora, String usuario, String texto, Long idVideo) {

}
