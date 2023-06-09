package com.serigrafia.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the productor database table.
 */
@Entity
@NamedQuery(name = "Productor.findAll", query = "SELECT p FROM Productor p")
public class Productor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // bi-directional many-to-one association to Envase
    @JsonIgnore
	@OneToMany(mappedBy = "productor")
    private List<Envase> envases;

    public Productor() {
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

    public List<Envase> getEnvases() {
        return this.envases;
    }

    public void setEnvases(List<Envase> envases) {
        this.envases = envases;
    }

    public Envase addEnvas(Envase envas) {
        getEnvases().add(envas);
        envas.setProductor(this);
        return envas;
    }

    public Envase removeEnvas(Envase envas) {
        getEnvases().remove(envas);
        envas.setProductor(null);
        return envas;
    }
}
