package com.ipartube.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuariosUserPass")
public class UsuarioUsuarioPassword extends Usuario {
	// 1. VARIABLES DE INSTANCIA
	@Column(unique = true)
	private String usuario;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	// 3. CONSTRUCTORES
	public UsuarioUsuarioPassword(Long id, String nombre, String usuario, String password) {
		super(id, nombre);
		// 3.1 CAMBIAR A SETTERS
		setUsuario(usuario);
		setPassword(password);
	}
	
	public UsuarioUsuarioPassword(Long id) {
		this(id, null, null, null);
	}

	// 2. GETTERS Y SETTERS
	public String getEmail() {
		return usuario;
	}

	public void setUsuario(String email) {
		this.usuario = email;
	}

	@Override
	public String getUsuario() {
		return usuario;
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
		return String.format("Usuario [id=%s, nombre=%s, email=%s, password=%s]", id, nombre, usuario, password);
	}

}
