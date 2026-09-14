package com.ipartube.entidades;

public class UsuarioUsuarioPassword extends Usuario {
	// 1. VARIABLES DE INSTANCIA
	private String usuario;
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
