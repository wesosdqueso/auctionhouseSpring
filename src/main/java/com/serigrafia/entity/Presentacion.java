package com.serigrafia.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the presentacion database table.
 */
@Entity
@NamedQuery(name = "Presentacion.findAll", query = "SELECT p FROM Presentacion p")
public class Presentacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String descripcion;

    // bi-directional many-to-one association to MovimientoPresentacion
    @JsonIgnore
	@OneToMany(mappedBy = "presentacion")
    private List<MovimientoPresentacion> movimientoPresentacions;

    // bi-directional many-to-one association to Envase
    @ManyToOne
    private Envase envase;

    // bi-directional many-to-one association to Marca
    @ManyToOne
    private Marca marca;

    // bi-directional many-to-one association to StockMensualPresentacion
    @JsonIgnore
	@OneToMany(mappedBy = "presentacion")
    private List<StockMensualPresentacion> stockMensualPresentacions;

    // bi-directional many-to-one association to StockPresentacion
    @JsonIgnore
	@OneToMany(mappedBy = "presentacion")
    private List<StockPresentacion> stockPresentacions;

    public Presentacion() {
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

    public List<MovimientoPresentacion> getMovimientoPresentacions() {
        return this.movimientoPresentacions;
    }

    public void setMovimientoPresentacions(List<MovimientoPresentacion> movimientoPresentacions) {
        this.movimientoPresentacions = movimientoPresentacions;
    }

    public MovimientoPresentacion addMovimientoPresentacion(MovimientoPresentacion movimientoPresentacion) {
        getMovimientoPresentacions().add(movimientoPresentacion);
        movimientoPresentacion.setPresentacion(this);
        return movimientoPresentacion;
    }

    public MovimientoPresentacion removeMovimientoPresentacion(MovimientoPresentacion movimientoPresentacion) {
        getMovimientoPresentacions().remove(movimientoPresentacion);
        movimientoPresentacion.setPresentacion(null);
        return movimientoPresentacion;
    }

    public Envase getEnvase() {
        return this.envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }

    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<StockMensualPresentacion> getStockMensualPresentacions() {
        return this.stockMensualPresentacions;
    }

    public void setStockMensualPresentacions(List<StockMensualPresentacion> stockMensualPresentacions) {
        this.stockMensualPresentacions = stockMensualPresentacions;
    }

    public StockMensualPresentacion addStockMensualPresentacion(StockMensualPresentacion stockMensualPresentacion) {
        getStockMensualPresentacions().add(stockMensualPresentacion);
        stockMensualPresentacion.setPresentacion(this);
        return stockMensualPresentacion;
    }

    public StockMensualPresentacion removeStockMensualPresentacion(StockMensualPresentacion stockMensualPresentacion) {
        getStockMensualPresentacions().remove(stockMensualPresentacion);
        stockMensualPresentacion.setPresentacion(null);
        return stockMensualPresentacion;
    }

    public List<StockPresentacion> getStockPresentacions() {
        return this.stockPresentacions;
    }

    public void setStockPresentacions(List<StockPresentacion> stockPresentacions) {
        this.stockPresentacions = stockPresentacions;
    }

    public StockPresentacion addStockPresentacion(StockPresentacion stockPresentacion) {
        getStockPresentacions().add(stockPresentacion);
        stockPresentacion.setPresentacion(this);
        return stockPresentacion;
    }

    public StockPresentacion removeStockPresentacion(StockPresentacion stockPresentacion) {
        getStockPresentacions().remove(stockPresentacion);
        stockPresentacion.setPresentacion(null);
        return stockPresentacion;
    }
}
