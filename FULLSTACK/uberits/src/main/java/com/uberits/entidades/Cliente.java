package com.uberits.entidades;

import java.util.ArrayList;
import java.util.Collection;

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
@Table(name = "clientes")
public class Cliente {
	@EqualsAndHashCode.Include
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

	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "cliente")
	private Collection<Pedido> pedidos = new ArrayList<>();

	@ToString.Exclude
	@NotNull
	@OneToOne(mappedBy = "cliente")
	private Usuario usuario;
}
