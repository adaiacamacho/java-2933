package com.ipartube.logicanegocio;

import java.util.Optional;
import java.util.logging.Logger;

import com.ipartube.accesodatos.DaoComentario;
import com.ipartube.accesodatos.DaoVideo;
import com.ipartube.entidades.Comentario;
import com.ipartube.entidades.Video;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocioImpl.class.getName());

	private static final DaoVideo VIDEO_DAO = (DaoVideo) ContenedorInyeccionDependencias.obtenerObjeto("dao.video");
	private static final DaoComentario COMENTARIO_DAO = (DaoComentario) ContenedorInyeccionDependencias.obtenerObjeto("dao.comentario");
	
	@Override
	public Iterable<Video> listarVideos() {
		log.info("Se ha pedido el listado de videos");

		return VIDEO_DAO.obtenerTodos();
	}

	@Override
	public Optional<Video> verDetalleVideo(Long id) {
		log.info("Se ha pedido el detalle del video " + id); 

		return VIDEO_DAO.obtenerPorId(id);
	}

	@Override
	public Iterable<Comentario> verComentariosVideo(Long idVideo) {
		log.info("Se ha pedido el listado de comentarios del video " + idVideo);

		return COMENTARIO_DAO.obtenerTodosPorIdVideo(idVideo);
	}

	@Override
	public Comentario crearNuevoComentario(Comentario comentario) {
		log.info("Se va a insertar un nuevo comentario " + comentario);

		return COMENTARIO_DAO.insertar(comentario);
	}

	@Override
	public Video crearNuevoVideo(Video video) {
		log.info("Se va a insertar un nuevo video " + video);

		return VIDEO_DAO.insertar(video);
	}

}
