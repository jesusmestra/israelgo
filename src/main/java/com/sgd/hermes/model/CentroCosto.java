/**
 * This file was generated by the JPA Modeler
 */
package com.sgd.hermes.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author jdmp
 */
@Entity
@Table(name = "centro_costo")
@NamedQueries({
    @NamedQuery(name = "CentroCosto.findByNombre", query = "Select c from CentroCosto c WHERE c.nombre =:nombre"),
    @NamedQuery(name = "CentroCosto.findByEmpresaAndNombre", query = "Select e from CentroCosto e WHERE e.empresa.id =:empresa.id AND e.nombre =:nombre")})
public class CentroCosto implements Serializable {

    @Column(name = "ccosto_id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ccosto_consecutivo_interno")
    @Basic
    private long consecutivoInterno;

    @Column(name = "ccosto_codigo")
    @Basic
    private String codigo;

    @Column(name = "ccosto_consecutivo_externo")
    @Basic
    private long consecutivoExterno;

    @Column(name = "ccosto_nombre")
    @Basic
    private String nombre;

    @ManyToOne(targetEntity = Tercero.class)
    @JoinColumn(name = "JEFE_ID")
    private Tercero jefe;

    @ManyToOne(targetEntity = Empresa.class)
    @JoinColumn(name = "EMPRESA_ID")
    private Empresa empresa;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getConsecutivoInterno() {
        return this.consecutivoInterno;
    }

    public void setConsecutivoInterno(long consecutivoInterno) {
        this.consecutivoInterno = consecutivoInterno;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public long getConsecutivoExterno() {
        return this.consecutivoExterno;
    }

    public void setConsecutivoExterno(long consecutivoExterno) {
        this.consecutivoExterno = consecutivoExterno;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tercero getJefe() {
        return this.jefe;
    }

    public void setJefe(Tercero jefe) {
        this.jefe = jefe;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
