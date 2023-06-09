package com.serigrafia.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the stock_envase database table.
 */
@Entity
@Table(name = "stock_envase")
@NamedQuery(name = "StockEnvase.findAll", query = "SELECT s FROM StockEnvase s")
public class StockEnvase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private int cantidad;

    // bi-directional many-to-one association to Almacen
    @ManyToOne
    private Almacen almacen;

    // bi-directional many-to-one association to Envase
    @ManyToOne
    private Envase envase;

    public StockEnvase() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Almacen getAlmacen() {
        return this.almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Envase getEnvase() {
        return this.envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }
}
