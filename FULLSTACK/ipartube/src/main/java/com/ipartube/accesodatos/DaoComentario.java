package com.ipartube.accesodatos;

import com.ipartube.entidades.Comentario;

import bibliotecas.accesodatos.Dao;

public interface DaoComentario extends Dao<Comentario> {

	Iterable<Comentario> obtenerTodosPorIdVideo(Long idVideo);

}
