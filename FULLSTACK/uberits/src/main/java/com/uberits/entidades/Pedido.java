package com.uberits.entidades;

import java.util.Collection;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Cliente cliente;

	@OneToMany
	private Collection<Linea> lineas;

	public Pedido(Long id, Cliente cliente, Collection<Linea> lineas) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.lineas = lineas;
	}

	public Pedido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Collection<Linea> getLinea() {
		return lineas;
	}

	public void setLinea(Collection<Linea> lineas) {
		this.lineas = lineas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, id, lineas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(id, other.id)
				&& Objects.equals(lineas, other.lineas);
	}

	@Override
	public String toString() {
		return String.format("Pedido [id=%s, cliente=%s, lineas=%s]", id, cliente, lineas);
	}

	@Entity
	@Table(name = "pedido_lineas")
	public static class Linea {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne
		private Plato plato;
		
		private Integer cantidad;

		public Linea(Long id, Plato plato, Integer cantidad) {
			super();
			this.id = id;
			this.plato = plato;
			this.cantidad = cantidad;
		}

		public Linea() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Plato getPlato() {
			return plato;
		}

		public void setPlato(Plato plato) {
			this.plato = plato;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}

		@Override
		public int hashCode() {
			return Objects.hash(cantidad, id, plato);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Linea other = (Linea) obj;
			return Objects.equals(cantidad, other.cantidad) && Objects.equals(id, other.id)
					&& Objects.equals(plato, other.plato);
		}

		@Override
		public String toString() {
			return String.format("Linea [id=%s, plato=%s, cantidad=%s]", id, plato, cantidad);
		}

	}
}
