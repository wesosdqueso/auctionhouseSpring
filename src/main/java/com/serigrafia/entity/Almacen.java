package com.serigrafia.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the almacen database table.
 */
@Entity
@NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a")
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false, unique = true)
    private String nombre;

    // bi-directional many-to-one association to MovimientoEnvase
    @JsonIgnore
	@OneToMany(mappedBy = "almacen1")
    private List<MovimientoEnvase> movimientoEnvases1;

    // bi-directional many-to-one association to MovimientoEnvase
    @JsonIgnore
	@OneToMany(mappedBy = "almacen2")
    private List<MovimientoEnvase> movimientoEnvases2;

    // bi-directional many-to-one association to MovimientoPresentacion
    @JsonIgnore
	@OneToMany(mappedBy = "almacen1")
    private List<MovimientoPresentacion> movimientoPresentacions1;

    // bi-directional many-to-one association to MovimientoPresentacion
    @JsonIgnore
	@OneToMany(mappedBy = "almacen2")
    private List<MovimientoPresentacion> movimientoPresentacions2;

    // bi-directional many-to-one association to StockEnvase
    @JsonIgnore
	@OneToMany(mappedBy = "almacen")
    private List<StockEnvase> stockEnvases;

    // bi-directional many-to-one association to StockMensualEnvase
    @JsonIgnore
	@OneToMany(mappedBy = "almacen")
    private List<StockMensualEnvase> stockMensualEnvases;

    // bi-directional many-to-one association to StockMensualPresentacion
    @JsonIgnore
	@OneToMany(mappedBy = "almacen")
    private List<StockMensualPresentacion> stockMensualPresentacions;

    // bi-directional many-to-one association to StockPresentacion
    @JsonIgnore
	@OneToMany(mappedBy = "almacen")
    private List<StockPresentacion> stockPresentacions;

    public Almacen() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MovimientoEnvase> getMovimientoEnvases1() {
        return this.movimientoEnvases1;
    }

    public void setMovimientoEnvases1(List<MovimientoEnvase> movimientoEnvases1) {
        this.movimientoEnvases1 = movimientoEnvases1;
    }

    public MovimientoEnvase addMovimientoEnvases1(MovimientoEnvase movimientoEnvases1) {
        getMovimientoEnvases1().add(movimientoEnvases1);
        movimientoEnvases1.setAlmacen1(this);
        return movimientoEnvases1;
    }

    public MovimientoEnvase removeMovimientoEnvases1(MovimientoEnvase movimientoEnvases1) {
        getMovimientoEnvases1().remove(movimientoEnvases1);
        movimientoEnvases1.setAlmacen1(null);
        return movimientoEnvases1;
    }

    public List<MovimientoEnvase> getMovimientoEnvases2() {
        return this.movimientoEnvases2;
    }

    public void setMovimientoEnvases2(List<MovimientoEnvase> movimientoEnvases2) {
        this.movimientoEnvases2 = movimientoEnvases2;
    }

    public MovimientoEnvase addMovimientoEnvases2(MovimientoEnvase movimientoEnvases2) {
        getMovimientoEnvases2().add(movimientoEnvases2);
        movimientoEnvases2.setAlmacen2(this);
        return movimientoEnvases2;
    }

    public MovimientoEnvase removeMovimientoEnvases2(MovimientoEnvase movimientoEnvases2) {
        getMovimientoEnvases2().remove(movimientoEnvases2);
        movimientoEnvases2.setAlmacen2(null);
        return movimientoEnvases2;
    }

    public List<MovimientoPresentacion> getMovimientoPresentacions1() {
        return this.movimientoPresentacions1;
    }

    public void setMovimientoPresentacions1(List<MovimientoPresentacion> movimientoPresentacions1) {
        this.movimientoPresentacions1 = movimientoPresentacions1;
    }

    public MovimientoPresentacion addMovimientoPresentacions1(MovimientoPresentacion movimientoPresentacions1) {
        getMovimientoPresentacions1().add(movimientoPresentacions1);
        movimientoPresentacions1.setAlmacen1(this);
        return movimientoPresentacions1;
    }

    public MovimientoPresentacion removeMovimientoPresentacions1(MovimientoPresentacion movimientoPresentacions1) {
        getMovimientoPresentacions1().remove(movimientoPresentacions1);
        movimientoPresentacions1.setAlmacen1(null);
        return movimientoPresentacions1;
    }

    public List<MovimientoPresentacion> getMovimientoPresentacions2() {
        return this.movimientoPresentacions2;
    }

    public void setMovimientoPresentacions2(List<MovimientoPresentacion> movimientoPresentacions2) {
        this.movimientoPresentacions2 = movimientoPresentacions2;
    }

    public MovimientoPresentacion addMovimientoPresentacions2(MovimientoPresentacion movimientoPresentacions2) {
        getMovimientoPresentacions2().add(movimientoPresentacions2);
        movimientoPresentacions2.setAlmacen2(this);
        return movimientoPresentacions2;
    }

    public MovimientoPresentacion removeMovimientoPresentacions2(MovimientoPresentacion movimientoPresentacions2) {
        getMovimientoPresentacions2().remove(movimientoPresentacions2);
        movimientoPresentacions2.setAlmacen2(null);
        return movimientoPresentacions2;
    }

    public List<StockEnvase> getStockEnvases() {
        return this.stockEnvases;
    }

    public void setStockEnvases(List<StockEnvase> stockEnvases) {
        this.stockEnvases = stockEnvases;
    }

    public StockEnvase addStockEnvas(StockEnvase stockEnvas) {
        getStockEnvases().add(stockEnvas);
        stockEnvas.setAlmacen(this);
        return stockEnvas;
    }

    public StockEnvase removeStockEnvas(StockEnvase stockEnvas) {
        getStockEnvases().remove(stockEnvas);
        stockEnvas.setAlmacen(null);
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
        stockMensualEnvas.setAlmacen(this);
        return stockMensualEnvas;
    }

    public StockMensualEnvase removeStockMensualEnvas(StockMensualEnvase stockMensualEnvas) {
        getStockMensualEnvases().remove(stockMensualEnvas);
        stockMensualEnvas.setAlmacen(null);
        return stockMensualEnvas;
    }

    public List<StockMensualPresentacion> getStockMensualPresentacions() {
        return this.stockMensualPresentacions;
    }

    public void setStockMensualPresentacions(List<StockMensualPresentacion> stockMensualPresentacions) {
        this.stockMensualPresentacions = stockMensualPresentacions;
    }

    public StockMensualPresentacion addStockMensualPresentacion(StockMensualPresentacion stockMensualPresentacion) {
        getStockMensualPresentacions().add(stockMensualPresentacion);
        stockMensualPresentacion.setAlmacen(this);
        return stockMensualPresentacion;
    }

    public StockMensualPresentacion removeStockMensualPresentacion(StockMensualPresentacion stockMensualPresentacion) {
        getStockMensualPresentacions().remove(stockMensualPresentacion);
        stockMensualPresentacion.setAlmacen(null);
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
        stockPresentacion.setAlmacen(this);
        return stockPresentacion;
    }

    public StockPresentacion removeStockPresentacion(StockPresentacion stockPresentacion) {
        getStockPresentacions().remove(stockPresentacion);
        stockPresentacion.setAlmacen(null);
        return stockPresentacion;
    }
}
