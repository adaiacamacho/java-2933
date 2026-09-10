package com.ipartube.logicanegocio;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ipartube.accesodatos.ComentarioCrud;
import com.ipartube.accesodatos.VideoCrud;
import com.ipartube.dtos.ComentarioDto;
import com.ipartube.dtos.ComentarioInsertarDto;
import com.ipartube.dtos.ComentarioInsertarRespuestaDto;
import com.ipartube.dtos.VideoDto;
import com.ipartube.dtos.VideoInsertarDto;
import com.ipartube.dtos.VideoInsertarRespuestaDto;

public class AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocio.class.getName());

	public static ArrayList<VideoDto> listarVideos() {
		log.info("Se ha pedido el listado de videos");

		return VideoCrud.obtenerTodos();
	}

	public static VideoDto verDetalleVideo(Long id) {
		log.info("Se ha pedido el detalle del video " + id);

		return VideoCrud.obtenerPorId(id);
	}
	
	public static ArrayList<ComentarioDto> verComentariosVideo(Long idVideo) {
		log.info("Se ha pedido el listado de comentarios del video " + idVideo);
		
		return ComentarioCrud.obtenerTodosPorIdVideo(idVideo);
	}

	public static ComentarioInsertarRespuestaDto crearNuevoComentario(ComentarioInsertarDto comentarioInsertar) {
		log.info("Se va a insertar un nuevo comentario " + comentarioInsertar);
		
		return ComentarioCrud.insertar(comentarioInsertar);
	}
	
	public static VideoInsertarRespuestaDto crearNuevoVideo(VideoInsertarDto videoInsertar) {
		log.info("Se va a insertar un nuevo video " + videoInsertar);
		
		return VideoCrud.insertar(videoInsertar);
	}
	
	
}
