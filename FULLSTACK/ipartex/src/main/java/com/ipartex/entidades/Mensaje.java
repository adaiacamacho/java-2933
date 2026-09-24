package com.ipartex.entidades;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensajes")
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario usuario;
	
	private String texto;
	
	@Column(name = "fecha_hora")
	private LocalDateTime fechaHora;

	public Mensaje(Long id, Usuario usuario, String texto, LocalDateTime fechaHora) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.texto = texto;
		this.fechaHora = fechaHora;
	}
	
	// NECESARIO PARA JACKSON (conversión de/hacia JSON) EN LOS SERVICIOS REST DE JERSEY
	public Mensaje() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public String getNombre() {
		return usuario.getNombre();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, id, usuario, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(fechaHora, other.fechaHora) && Objects.equals(id, other.id)
				&& Objects.equals(usuario, other.usuario) && Objects.equals(texto, other.texto);
	}

	@Override
	public String toString() {
		return String.format("Mensaje [id=%s, usuario=%s, texto=%s, fechaHora=%s]", id, usuario, texto, fechaHora);
	}

}
