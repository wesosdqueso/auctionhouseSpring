package com.serigrafia.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the marca database table.
 */
@Entity
@NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m")
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // bi-directional many-to-one association to Presentacion
    @JsonIgnore
	@OneToMany(mappedBy = "marca")
    private List<Presentacion> presentacions;

    public Marca() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Presentacion> getPresentacions() {
        return this.presentacions;
    }

    public void setPresentacions(List<Presentacion> presentacions) {
        this.presentacions = presentacions;
    }

    public Presentacion addPresentacion(Presentacion presentacion) {
        getPresentacions().add(presentacion);
        presentacion.setMarca(this);
        return presentacion;
    }

    public Presentacion removePresentacion(Presentacion presentacion) {
        getPresentacions().remove(presentacion);
        presentacion.setMarca(null);
        return presentacion;
    }
}
