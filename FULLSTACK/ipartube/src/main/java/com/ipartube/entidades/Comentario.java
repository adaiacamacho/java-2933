package com.ipartube.entidades;

import java.time.LocalDateTime;

public class Comentario {
	private Long id;
	private LocalDateTime fechaHora;
	private String texto;

	private Video video;
	private Usuario usuario;

	public Comentario(Long id, LocalDateTime fechaHora, String texto, Video video, Usuario usuario) {
		super();
		setId(id);
		setFechaHora(fechaHora);
		setTexto(texto);
		setVideo(video);
		setUsuario(usuario);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return String.format("Comentario [id=%s, fechaHora=%s, texto=%s, video=%s, usuario=%s]", id, fechaHora, texto,
				video, usuario);
	}

}
