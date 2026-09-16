package com.ipartex.accesodatos.arraylist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import com.ipartex.accesodatos.AccesoDatosException;
import com.ipartex.accesodatos.DaoMensaje;
import com.ipartex.entidades.Mensaje;

public class DaoMensajeArrayListMejorada implements DaoMensaje {
	private ArrayList<Mensaje> mensajes = new ArrayList<>();

	public DaoMensajeArrayListMejorada() {
		mensajes.add(new Mensaje(1L, "Javier", "Hola", LocalDateTime.now()));
		mensajes.add(new Mensaje(2L, "Pepe", "¡Qué pasa!", LocalDateTime.now()));
		mensajes.add(new Mensaje(3L, "Javier", "¡¡Whatsuppppppp!!", LocalDateTime.now()));
		mensajes.add(new Mensaje(4L, "Pedro", "Facepalm", LocalDateTime.now()));
	}

	@Override
	public Iterable<Mensaje> obtenerTodos() {
		return mensajes;
	}

	@Override
	public Optional<Mensaje> obtenerPorId(Long id) {
		return mensajes.stream().filter(m -> m.getId() == id).findFirst();
	}

	@Override
	public Mensaje insertar(Mensaje mensaje) {
		Long idNuevo = mensajes.size() == 0 ? 1L
				: mensajes.stream().map(m -> m.getId()).reduce(0L, (resultado, id) -> id > resultado ? id : resultado)
						+ 1;

		mensaje.setId(idNuevo);

		mensajes.add(mensaje);

		return mensaje;
	}

	@Override
	public Mensaje modificar(Mensaje mensajeModificado) {
		Mensaje mensajeOriginal = obtenerPorId(mensajeModificado.getId())
				.orElseThrow(() -> new AccesoDatosException("No se ha encontrado el registro a modificar"));

		mensajes.set(mensajes.indexOf(mensajeOriginal), mensajeModificado);

		return mensajeModificado;
	}

	@Override
	public void borrar(Long id) {
		mensajes.remove(obtenerPorId(id).get());
	}

}
