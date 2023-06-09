package com.serigrafia.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the envase database table.
 */
@Entity
@NamedQuery(name = "Envase.findAll", query = "SELECT e FROM Envase e")
public class Envase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String descripcion;

    // bi-directional many-to-one association to Productor
    @ManyToOne
    private Productor productor;

    // bi-directional many-to-one association to MovimientoEnvase
    @JsonIgnore
	@OneToMany(mappedBy = "envase")
    private List<MovimientoEnvase> movimientoEnvases;

    // bi-directional many-to-one association to Presentacion
    @JsonIgnore
	@OneToMany(mappedBy = "envase")
    private List<Presentacion> presentacions;

    // bi-directional many-to-one association to StockEnvase
    @JsonIgnore
	@OneToMany(mappedBy = "envase")
    private List<StockEnvase> stockEnvases;

    // bi-directional many-to-one association to StockMensualEnvase
    @JsonIgnore
	@OneToMany(mappedBy = "envase")
    private List<StockMensualEnvase> stockMensualEnvases;

    public Envase() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Productor getProductor() {
        return this.productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public List<MovimientoEnvase> getMovimientoEnvases() {
        return this.movimientoEnvases;
    }

    public void setMovimientoEnvases(List<MovimientoEnvase> movimientoEnvases) {
        this.movimientoEnvases = movimientoEnvases;
    }

    public MovimientoEnvase addMovimientoEnvas(MovimientoEnvase movimientoEnvas) {
        getMovimientoEnvases().add(movimientoEnvas);
        movimientoEnvas.setEnvase(this);
        return movimientoEnvas;
    }

    public MovimientoEnvase removeMovimientoEnvas(MovimientoEnvase movimientoEnvas) {
        getMovimientoEnvases().remove(movimientoEnvas);
        movimientoEnvas.setEnvase(null);
        return movimientoEnvas;
    }

    public List<Presentacion> getPresentacions() {
        return this.presentacions;
    }

    public void setPresentacions(List<Presentacion> presentacions) {
        this.presentacions = presentacions;
    }

    public Presentacion addPresentacion(Presentacion presentacion) {
        getPresentacions().add(presentacion);
        presentacion.setEnvase(this);
        return presentacion;
    }

    public Presentacion removePresentacion(Presentacion presentacion) {
        getPresentacions().remove(presentacion);
        presentacion.setEnvase(null);
        return presentacion;
    }

    public List<StockEnvase> getStockEnvases() {
        return this.stockEnvases;
    }

    public void setStockEnvases(List<StockEnvase> stockEnvases) {
        this.stockEnvases = stockEnvases;
    }

    public StockEnvase addStockEnvas(StockEnvase stockEnvas) {
        getStockEnvases().add(stockEnvas);
        stockEnvas.setEnvase(this);
        return stockEnvas;
    }

    public StockEnvase removeStockEnvas(StockEnvase stockEnvas) {
        getStockEnvases().remove(stockEnvas);
        stockEnvas.setEnvase(null);
        return stockEnvas;
    }

    public List<StockMensualEnvase> getStockMensualEnvases() {
        return this.stockMensualEnvases;
    }

    public void setStockMensualEnvases(List<StockMensualEnvase> stockMensualEnvases) {
        this.stockMensualEnvases = stockMensualEnvases;
    }

    public StockMensualEnvase addStockMensualEnvas(StockMensualEnvase stockMensualEnvas) {
        getStockMensualEnvases().add(stockMensualEnvas);
        stockMensualEnvas.setEnvase(this);
        return stockMensualEnvas;
    }

    public StockMensualEnvase removeStockMensualEnvas(StockMensualEnvase stockMensualEnvas) {
        getStockMensualEnvases().remove(stockMensualEnvas);
        stockMensualEnvas.setEnvase(null);
        return stockMensualEnvas;
    }
}
