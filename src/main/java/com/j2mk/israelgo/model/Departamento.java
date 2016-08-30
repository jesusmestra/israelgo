/**
 * This file was generated by the JPA Modeler
 */
package com.j2mk.israelgo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author jkelsy
 */
@Entity
@Table(name = "departamento")
@NamedQuery(name = "Departamento.findByCodigo", query = "Select e from Departamento e WHERE e.codigo = :codigo")
public class Departamento implements Serializable {

    @Column(name = "dpto_id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dpto_codigo")
    @Basic
    private String codigo;

    @Column(name = "dpto_nombre")
    @Basic
    private String nombre;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}