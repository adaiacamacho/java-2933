package com.uberits.entidades;

import java.util.Collection;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurantes")
public class Restaurante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;

	@ManyToMany
	private Collection<TipoComida> tiposComida;

	@OneToMany
	private Collection<Plato> platos;

	public Restaurante(Long id, String nombre, Collection<TipoComida> tiposComida, Collection<Plato> platos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tiposComida = tiposComida;
		this.platos = platos;
	}

	public Restaurante() {
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

	public Collection<TipoComida> getTiposComida() {
		return tiposComida;
	}

	public void setTiposComida(Collection<TipoComida> tiposComida) {
		this.tiposComida = tiposComida;
	}

	public Collection<Plato> getPlato() {
		return platos;
	}

	public void setPlato(Collection<Plato> platos) {
		this.platos = platos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, platos, tiposComida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurante other = (Restaurante) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(platos, other.platos) && Objects.equals(tiposComida, other.tiposComida);
	}

	@Override
	public String toString() {
		return String.format("Restaurante [id=%s, nombre=%s, tiposComida=%s, plato=%s]", id, nombre, tiposComida,
				platos);
	}

}
