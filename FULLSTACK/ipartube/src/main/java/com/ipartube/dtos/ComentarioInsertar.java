package com.ipartube.dtos;

import java.time.LocalDateTime;

public record ComentarioInsertar(LocalDateTime fechaHora, String usuario, String texto, Long idVideo) {

}
