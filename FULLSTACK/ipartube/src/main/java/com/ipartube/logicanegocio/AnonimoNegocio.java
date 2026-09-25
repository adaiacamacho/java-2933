package com.ipartube.logicanegocio;

import java.util.Optional;

import com.ipartube.entidades.Comentario;
import com.ipartube.entidades.Video;

public interface AnonimoNegocio {
	public Iterable<Video> listarVideos();
	public Optional<Video> verDetalleVideo(Long id);
	public Video crearNuevoVideo(Video video);
	
	public Iterable<Comentario> verComentariosVideo(Long idVideo);
	public Comentario crearNuevoComentario(Comentario comentario);

}
