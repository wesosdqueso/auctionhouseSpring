package com.serigrafia.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the movimiento_envase database table.
 */
@Entity
@Table(name = "movimiento_envase")
@NamedQuery(name = "MovimientoEnvase.findAll", query = "SELECT m FROM MovimientoEnvase m")
public class MovimientoEnvase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private int cantidad;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    @Column(name = "num_guia")
    private String numGuia;

    // bi-directional many-to-one association to Almacen
    @ManyToOne
    @JoinColumn(name = "almacen_origen")
    private Almacen almacen1;

    // bi-directional many-to-one association to Almacen
    @ManyToOne
    @JoinColumn(name = "almacen_destino")
    private Almacen almacen2;

    // bi-directional many-to-one association to Envase
    @ManyToOne
    private Envase envase;

    public MovimientoEnvase() {
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

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumGuia() {
        return this.numGuia;
    }

    public void setNumGuia(String numGuia) {
        this.numGuia = numGuia;
    }

    public Almacen getAlmacen1() {
        return this.almacen1;
    }

    public void setAlmacen1(Almacen almacen1) {
        this.almacen1 = almacen1;
    }

    public Almacen getAlmacen2() {
        return this.almacen2;
    }

    public void setAlmacen2(Almacen almacen2) {
        this.almacen2 = almacen2;
    }

    public Envase getEnvase() {
        return this.envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }
}
