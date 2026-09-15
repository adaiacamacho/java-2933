package com.ipartex.accesodatos.arraylist;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.entidades.Mensaje;

public class DaoMensajeArrayList implements DaoMensaje {
	private ArrayList<Mensaje> mensajes = new ArrayList<>();
	
	public DaoMensajeArrayList() {
		mensajes.add(new Mensaje(1L, "Javier", "Hola", LocalDateTime.now()));
		mensajes.add(new Mensaje(2L, "Pepe", "¡Qué pasa!", LocalDateTime.now()));
		mensajes.add(new Mensaje(3L, "Javier", "¡¡Whatsuppppppp!!", LocalDateTime.now()));
		mensajes.add(new Mensaje(4L, "Pedro", "Facepalm", LocalDateTime.now()));
	}

	@Override
	public Iterable<Mensaje> obtenerTodos() {
		return mensajes;
	}
	
	
}
