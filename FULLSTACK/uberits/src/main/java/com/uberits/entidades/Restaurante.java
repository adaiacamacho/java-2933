package com.uberits.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String nombre;

    @ManyToMany
    @JoinTable(
        name = "restaurante_tipo_comida",
        joinColumns = @JoinColumn(name = "restaurante_id"),
        inverseJoinColumns = @JoinColumn(name = "tipo_comida_id")
    )
    private Collection<TipoComida> tiposComida = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    private Collection<Plato> platos = new ArrayList<>();

    public Restaurante() {
    }

    public Restaurante(
        Long id,
        String nombre
    ) {
        this.id = id;
        this.nombre = nombre;
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

    public Collection<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(Collection<Plato> platos) {
        this.platos = platos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Restaurante other = (Restaurante) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
            "Restaurante [id=%s, nombre=%s]",
            id, nombre
        );
    }
}
