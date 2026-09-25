package com.ipartube.accesodatos.jpa;

import com.ipartube.accesodatos.DaoVideo;
import com.ipartube.entidades.Video;

import bibliotecas.accesodatos.DaoJpa;

public class VideoCrud extends DaoJpa<Video> implements DaoVideo {
	public VideoCrud() {
		super(Video.class);
	}
	// TODO: Añadir relación con Usuario
}
