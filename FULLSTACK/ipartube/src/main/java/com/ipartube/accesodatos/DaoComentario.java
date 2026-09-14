package com.ipartube.accesodatos;

import java.util.ArrayList;

import com.ipartube.dtos.ComentarioDto;
import com.ipartube.entidades.Comentario;

import bibliotecas.accesodatos.Dao;

public interface DaoComentario extends Dao<Comentario> {

	ArrayList<ComentarioDto> obtenerTodosPorIdVideo(Long idVideo);

}
