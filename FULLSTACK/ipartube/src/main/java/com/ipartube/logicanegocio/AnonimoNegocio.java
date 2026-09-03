package com.ipartube.logicanegocio;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ipartube.accesodatos.ComentarioCrud;
import com.ipartube.accesodatos.VideoCrud;
import com.ipartube.dtos.Comentario;
import com.ipartube.dtos.ComentarioInsertar;
import com.ipartube.dtos.ComentarioInsertarRespuesta;
import com.ipartube.dtos.Video;

public class AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocio.class.getName());

	public static ArrayList<Video> listarVideos() {
		log.info("Se ha pedido el listado de videos");

		return VideoCrud.obtenerTodos();
	}

	public static Video verDetalleVideo(Long id) {
		log.info("Se ha pedido el detalle del video " + id);

		return VideoCrud.obtenerPorId(id);
	}
	
	public static ArrayList<Comentario> verComentariosVideo(Long idVideo) {
		log.info("Se ha pedido el listado de comentarios del video " + idVideo);
		
		return ComentarioCrud.obtenerTodosPorIdVideo(idVideo);
	}

	public static ComentarioInsertarRespuesta crearNuevoComentario(ComentarioInsertar comentarioInsertar) {
		return ComentarioCrud.insertar(comentarioInsertar);
	}
}
