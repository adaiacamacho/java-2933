package com.ipartube.entidades;

public class Usuario {
	// 1. VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;
	private String email;
	private String password;

	// 3. CONSTRUCTORES
	public Usuario(Long id, String nombre, String email, String password) {
		super();
		// 3.1 CAMBIAR A SETTERS
		setId(id);
		setNombre(nombre);
		setEmail(email);
		setPassword(password);
	}

	public Usuario(Long id) {
		this(id, null, null, null);
	}

	// 2. GETTERS Y SETTERS
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
