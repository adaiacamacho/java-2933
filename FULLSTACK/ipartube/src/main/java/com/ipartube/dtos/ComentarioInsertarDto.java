package com.ipartube.dtos;

import java.time.LocalDateTime;

public record ComentarioInsertarDto(LocalDateTime fechaHora, Long idUsuario, String texto, Long idVideo) {

}
