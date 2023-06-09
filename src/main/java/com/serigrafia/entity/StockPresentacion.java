package com.serigrafia.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the stock_presentacion database table.
 */
@Entity
@Table(name = "stock_presentacion")
@NamedQuery(name = "StockPresentacion.findAll", query = "SELECT s FROM StockPresentacion s")
public class StockPresentacion implements Serializable {

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

    // bi-directional many-to-one association to Presentacion
    @ManyToOne
    private Presentacion presentacion;

    public StockPresentacion() {
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

    public Presentacion getPresentacion() {
        return this.presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }
}
