package com.ipartube.entidades;

import java.time.LocalDate;

public class VideoDestacado extends Video {
	private LocalDate fechaDestacado;

	public VideoDestacado(Long id, LocalDate fecha, String url, String titulo, String descripcion, Usuario usuario,
			LocalDate fechaDestacado) {
		super(id, fecha, url, titulo, descripcion, usuario);
		setFechaDestacado(fechaDestacado);
	}
	
	public VideoDestacado(LocalDate fecha, String url, String titulo, String descripcion, Usuario usuario,
			LocalDate fechaDestacado) {
		this(null, fecha, url, titulo, descripcion, usuario, fechaDestacado);		
	}
	
	public VideoDestacado() {
		super();
	}
	
	public LocalDate getFechaDestacado() {
		return fechaDestacado;
	}

	public void setFechaDestacado(LocalDate fechaDestacado) {
		this.fechaDestacado = fechaDestacado;
	}

	public boolean isDestacado() {
		return LocalDate.now().isEqual(fechaDestacado);
	}
	
	@Override
	public String toString() {
		return String.format(
				"VideoDestacado [id=%s, fecha=%s, url=%s, titulo=%s, descripcion=%s, usuario=%s, fechaDestacado=%s]",
				id, fecha, url, titulo, descripcion, usuario, fechaDestacado);
	}

	
}
