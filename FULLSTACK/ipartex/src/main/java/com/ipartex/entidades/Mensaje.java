package com.ipartex.entidades;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensajes")
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String texto;
	
	@Column(name = "fecha_hora")
	private LocalDateTime fechaHora;

	public Mensaje(Long id, String nombre, String texto, LocalDateTime fechaHora) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, id, nombre, texto);
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
				&& Objects.equals(nombre, other.nombre) && Objects.equals(texto, other.texto);
	}

	@Override
	public String toString() {
		return String.format("Mensaje [id=%s, nombre=%s, texto=%s, fechaHora=%s]", id, nombre, texto, fechaHora);
	}

}
