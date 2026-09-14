package com.ipartube.entidades;

public abstract class Usuario {
	// 1. VARIABLES DE INSTANCIA
	protected Long id;
	protected String nombre;
	
	// 3. CONSTRUCTORES
	public Usuario(Long id, String nombre) {
		super();
		// 3.1 CAMBIAR A SETTERS
		setId(id);
		setNombre(nombre);
	}

	public Usuario(Long id) {
		this(id, null);
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
	
	public abstract String getUsuario();
	public abstract String getPassword();

	@Override
	public String toString() {
		return String.format("Usuario [id=%s, nombre=%s]", id, nombre);
	}

}
