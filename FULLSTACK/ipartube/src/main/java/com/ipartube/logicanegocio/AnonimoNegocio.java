package com.ipartube.logicanegocio;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ipartube.accesodatos.VideoCrud;
import com.ipartube.dtos.Video;

public class AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocio.class.getName());
	
	public static ArrayList<Video> listarVideos() {
		log.info("Se ha pedido el listado de videos");
		
		return VideoCrud.obtenerTodos();
	}
}
