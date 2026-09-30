package com.uberits.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime fechaHora = LocalDateTime.now();
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(
        mappedBy = "pedido",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private Collection<Linea> lineas = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Long id, Cliente cliente, Collection<Linea> lineas) {
        this.id = id;
        this.cliente = cliente;
        this.lineas = lineas;
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

    public Collection<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(Collection<Linea> lineas) {
        this.lineas = lineas;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pedido other = (Pedido) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
            "Pedido [id=%s, cliente=%s, lineas=%s]",
            id, cliente, lineas
        );
    }

    @Entity
    @Table(name = "pedido_lineas")
    public static class Linea {

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

        public Linea() {
        }

        public Linea(Long id, Pedido pedido, Plato plato, Integer cantidad) {
            this.id = id;
            this.pedido = pedido;
            this.plato = plato;
            this.cantidad = cantidad;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Pedido getPedido() {
            return pedido;
        }

        public void setPedido(Pedido pedido) {
            this.pedido = pedido;
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
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Linea other = (Linea) obj;
            return Objects.equals(id, other.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return String.format(
                "Linea [id=%s, plato=%s, cantidad=%s]",
                id, plato, cantidad
            );
        }
    }
}
