package com.ipartube.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuariosEmailPass")
public class UsuarioEmailPassword extends Usuario {
	// 1. VARIABLES DE INSTANCIA
	@Column(unique = true)
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	// 3. CONSTRUCTORES
	public UsuarioEmailPassword(Long id, String nombre, String email, String password) {
		super(id, nombre);
		// 3.1 CAMBIAR A SETTERS
		setEmail(email);
		setPassword(password);
	}

	public UsuarioEmailPassword(Long id) {
		this(id, null, null, null);
	}

	// 2. GETTERS Y SETTERS
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getUsuario() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 4. TOSTRING
	@Override
	public String toString() {
		return String.format("Usuario [id=%s, nombre=%s, email=%s, password=%s]", id, nombre, email, password);
	}

}
