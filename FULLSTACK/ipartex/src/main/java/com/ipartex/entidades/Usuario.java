package com.ipartex.entidades;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String user;
	private String email;
	private String contra;
	
	public Usuario(Long id, String user, String email, String contra) {
		super();
		this.id = id;
		this.user = user;
		this.email = email;
		this.contra = contra;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}	
	
	@Override
	public int hashCode() {
		return Objects.hash(contra, email, id, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(contra, other.contra) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return String.format("Usuario [id=%s, user=%s, email=%s, contra=%s]", id, user, email, contra);
	}
	
}
