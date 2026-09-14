package com.ipartube.logicanegocio;

import java.util.ArrayList;

import com.ipartube.dtos.ComentarioDto;
import com.ipartube.dtos.ComentarioInsertarDto;
import com.ipartube.dtos.ComentarioInsertarRespuestaDto;
import com.ipartube.dtos.VideoDto;
import com.ipartube.dtos.VideoInsertarDto;
import com.ipartube.dtos.VideoInsertarRespuestaDto;

public interface AnonimoNegocio {
	public ArrayList<VideoDto> listarVideos();
	public VideoDto verDetalleVideo(Long id);
	public VideoInsertarRespuestaDto crearNuevoVideo(VideoInsertarDto videoInsertar);
	
	public ArrayList<ComentarioDto> verComentariosVideo(Long idVideo);
	public ComentarioInsertarRespuestaDto crearNuevoComentario(ComentarioInsertarDto comentarioInsertar);
}
