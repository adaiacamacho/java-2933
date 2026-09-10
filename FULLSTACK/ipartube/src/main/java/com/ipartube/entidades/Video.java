package com.ipartube.entidades;

import java.time.LocalDate;

public class Video {
	private Long id;
	private LocalDate fecha;
	private String url;
	private String titulo;
	private String descripcion;
	
	private Usuario usuario;

	public Video(Long id, LocalDate fecha, String url, String titulo, String descripcion, Usuario usuario) {
		super();
		setId(id);
		setFecha(fecha);
		setUrl(url);
		setTitulo(titulo);
		setDescripcion(descripcion);
		setUsuario(usuario);
	}
	
	public Video(Long id, LocalDate fecha, String url, String titulo, String descripcion) {
		this(id, fecha, url, titulo, descripcion, null);
	}

	public Video(LocalDate fecha, String url, String titulo, String descripcion) {
		this(null, fecha, url, titulo, descripcion, null);
	}

	public Video(String url, String titulo, String descripcion, Usuario usuario) {
		this(null, LocalDate.now(), url, titulo, descripcion, usuario);
	}

	public Video(String url, String titulo, String descripcion) {
		this(null, LocalDate.now(), url, titulo, descripcion, null);
	}

	public Video(String url, String titulo) {
		this(null, LocalDate.now(), url, titulo, null, null);
	}

	public Video() {
		this(null, LocalDate.now(), "https://videoderickroll", "Rickroll", null, null);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (url == null || url.isBlank()) {
			throw new RuntimeException("La URL es obligatorio");
		}

		if (!url.startsWith("https://")) {
			throw new RuntimeException("La URL debe tener el formato adecuado");
		}

		this.url = url.trim();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null || titulo.isBlank()) {
			throw new RuntimeException("El título es obligatorio");
		}

		this.titulo = titulo.trim();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return String.format("Video [id=%s, fecha=%s, url=%s, titulo=%s, descripcion=%s, usuario=%s]", id, fecha, url,
				titulo, descripcion, usuario);
	}

}
