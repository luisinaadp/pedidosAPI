package com.todocode.pedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    private Long id;
    private String propietario;
    private String producto;
    private BigDecimal total;

    protected Pedido() {
    }

    public Pedido(Long id, String propietario, String producto, BigDecimal total) {
        this.id = id;
        this.propietario = propietario;
        this.producto = producto;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getProducto() {
        return producto;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
