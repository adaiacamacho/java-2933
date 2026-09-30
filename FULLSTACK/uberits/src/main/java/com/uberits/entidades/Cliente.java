package com.uberits.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 9, max = 9)
	@Pattern(regexp = "^[\\dXYZ]\\d{7}[A-Z]$")
	@Column(unique = true, columnDefinition = "CHAR(9)")
	private String nif;

	@NotBlank
	@Size(max = 40)
	private String nombre;

	@NotBlank
	@Size(min = 9, max = 9)
	@Pattern(regexp = "^\\d{9}$")
	@Column(columnDefinition = "CHAR(9)")
	private String telefono;

	@NotBlank
	@Size(max = 50)
	private String direccion;

	@OneToMany(mappedBy = "cliente")
	private Collection<Pedido> pedidos = new ArrayList<>();

	@NotNull
	@OneToOne(mappedBy = "cliente")
	private Usuario usuario;

	public Cliente() {
	}

	public Cliente(Long id, String nif, String nombre, String telefono, String direccion, Usuario usuario) {
		this.id = id;
		this.nif = nif;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Collection<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Collection<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return String.format("Cliente [id=%s, nif=%s, nombre=%s, telefono=%s, direccion=%s]", id, nif, nombre, telefono,
				direccion);
	}
}
