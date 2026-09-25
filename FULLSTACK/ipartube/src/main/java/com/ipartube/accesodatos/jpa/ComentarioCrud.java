package com.ipartube.accesodatos.jpa;

import com.ipartube.accesodatos.DaoComentario;
import com.ipartube.entidades.Comentario;

import bibliotecas.accesodatos.DaoJpa;

public class ComentarioCrud extends DaoJpa<Comentario> implements DaoComentario {
	public ComentarioCrud() {
		super(Comentario.class);
	}

	public Iterable<Comentario> obtenerTodosPorIdVideo(Long idVideo) {
		
			return null;
			//return jpa.ejecutarJpa(em -> em.createQuery("from Comentario c where c.video.id= :idVideo"));

	}


}
