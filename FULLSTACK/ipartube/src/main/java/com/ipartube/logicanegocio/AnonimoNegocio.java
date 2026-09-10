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
import com.ipartube.entidades.Usuario;
import com.ipartube.entidades.Video;

public class AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocio.class.getName());

	public static ArrayList<VideoDto> listarVideos() {
		log.info("Se ha pedido el listado de videos");

		ArrayList<Video> videos = VideoCrud.obtenerTodos();
		
		ArrayList<VideoDto> videoDtos = new ArrayList<VideoDto>();
		
		VideoDto videoDto;
		
		for(Video video: videos) {
			videoDto = new VideoDto(video.getId(), video.getFecha(), video.getUrl(), video.getTitulo(), video.getDescripcion());
			videoDtos.add(videoDto);
		}
		
		return videoDtos;
	}

	public static VideoDto verDetalleVideo(Long id) {
		log.info("Se ha pedido el detalle del video " + id);

		Video video = VideoCrud.obtenerPorId(id);
		
		return new VideoDto(video.getId(), video.getFecha(), video.getUrl(), video.getTitulo(), video.getDescripcion());
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
		
		Video video = VideoCrud.insertar(new Video(videoInsertar.url(), videoInsertar.titulo(), videoInsertar.descripcion(), new Usuario(videoInsertar.idUsuario())));
		
		return new VideoInsertarRespuestaDto(video.getId(), video.getFecha(), video.getUrl(), video.getTitulo(), video.getDescripcion(), videoInsertar.idUsuario());
	}
	
	
}
