package com.ipartube.logicanegocio;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ipartube.accesodatos.ComentarioCrud;
import com.ipartube.accesodatos.DaoComentario;
import com.ipartube.accesodatos.DaoVideo;
import com.ipartube.accesodatos.VideoCrud;
import com.ipartube.dtos.ComentarioDto;
import com.ipartube.dtos.ComentarioInsertarDto;
import com.ipartube.dtos.ComentarioInsertarRespuestaDto;
import com.ipartube.dtos.VideoDto;
import com.ipartube.dtos.VideoInsertarDto;
import com.ipartube.dtos.VideoInsertarRespuestaDto;
import com.ipartube.entidades.Comentario;
import com.ipartube.entidades.Usuario;
import com.ipartube.entidades.UsuarioEmailPassword;
import com.ipartube.entidades.Video;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocioImpl.class.getName());

	private static final DaoVideo VIDEO_DAO = new VideoCrud();
	private static final DaoComentario COMENTARIO_DAO = new ComentarioCrud();
	
	@Override
	public ArrayList<VideoDto> listarVideos() {
		log.info("Se ha pedido el listado de videos");

		ArrayList<Video> videos = VIDEO_DAO.obtenerTodos();

		ArrayList<VideoDto> videoDtos = new ArrayList<VideoDto>();

		VideoDto videoDto;

		for (Video video : videos) {
			videoDto = new VideoDto(video.getId(), video.getFecha(), video.getUrl(), video.getTitulo(),
					video.getDescripcion());
			videoDtos.add(videoDto);
		}

		return videoDtos;
	}

	@Override
	public VideoDto verDetalleVideo(Long id) {
		log.info("Se ha pedido el detalle del video " + id);

		Video video = VIDEO_DAO.obtenerPorId(id);

		return new VideoDto(video.getId(), video.getFecha(), video.getUrl(), video.getTitulo(), video.getDescripcion());
	}

	@Override
	public ArrayList<ComentarioDto> verComentariosVideo(Long idVideo) {
		log.info("Se ha pedido el listado de comentarios del video " + idVideo);

		return COMENTARIO_DAO.obtenerTodosPorIdVideo(idVideo);
	}

	@Override
	public ComentarioInsertarRespuestaDto crearNuevoComentario(ComentarioInsertarDto comentarioInsertar) {
		log.info("Se va a insertar un nuevo comentario " + comentarioInsertar);

		Usuario usuario = new UsuarioEmailPassword(comentarioInsertar.idUsuario());
		Video video = new Video(comentarioInsertar.idVideo(), null, null, null, null);
		Comentario comentario = new Comentario(null, comentarioInsertar.fechaHora(), comentarioInsertar.texto(), video,
				usuario);

		ComentarioInsertarRespuestaDto comentarioInsertarRespuestaDto = new ComentarioInsertarRespuestaDto(
				comentario.getId(), comentario.getFechaHora(), comentario.getUsuario().getId(), comentario.getTexto(),
				comentario.getVideo().getId());

		return comentarioInsertarRespuestaDto;
	}

	@Override
	public VideoInsertarRespuestaDto crearNuevoVideo(VideoInsertarDto videoInsertar) {
		log.info("Se va a insertar un nuevo video " + videoInsertar);

		Video video = VIDEO_DAO.insertar(new Video(videoInsertar.url(), videoInsertar.titulo(),
				videoInsertar.descripcion(), new UsuarioEmailPassword(videoInsertar.idUsuario())));

		return new VideoInsertarRespuestaDto(video.getId(), video.getFecha(), video.getUrl(), video.getTitulo(),
				video.getDescripcion(), videoInsertar.idUsuario());
	}

}
