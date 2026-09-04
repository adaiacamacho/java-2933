package com.ipartube.dtos;

import java.time.LocalDateTime;

public record ComentarioInsertar(LocalDateTime fechaHora, Long idUsuario, String texto, Long idVideo) {

}
