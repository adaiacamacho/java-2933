package com.uberits.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "pedidos")
public class Pedido {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Builder.Default
	@NotNull
	private LocalDateTime fechaHora = LocalDateTime.now();

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Collection<Linea> lineas = new ArrayList<>();

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	
	@Entity
	@Table(name = "pedido_lineas")
	public static class Linea {
		@EqualsAndHashCode.Include
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull
		@ManyToOne
		@JoinColumn(name = "pedido_id")
		private Pedido pedido;

		@NotNull
		@ManyToOne
		@JoinColumn(name = "plato_id")
		private Plato plato;

		@NotNull
		@Min(0)
		private Integer cantidad;
	}
}
