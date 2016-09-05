/**
 * This file was generated by the JPA Modeler
 */
package com.j2mk.israelgo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author jdmp
 */
@Entity
@Table(name = "plan_pago")
public class PlanPago implements Serializable {

    @Column(name = "pp_id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pp_numero_cuota")
    @Basic
    private Integer numeroCuota;

    @Column(name = "pp_fecha_pactada")
    @Basic
    private Date fechaPactada;

    @Column(name = "pp_valor_pactado")
    @Basic
    private Double valorPactado;

    @Column(name = "pp_observacion")
    @Basic
    private String observacion;

    @ManyToOne(targetEntity = Negociacion.class)
    @JoinColumn(name = "NEGOCIACION_ID")
    private Negociacion negociacion;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCuota() {
        return this.numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaPactada() {
        return this.fechaPactada;
    }

    public void setFechaPactada(Date fechaPactada) {
        this.fechaPactada = fechaPactada;
    }

    public Double getValorPactado() {
        return this.valorPactado;
    }

    public void setValorPactado(Double valorPactado) {
        this.valorPactado = valorPactado;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Negociacion getNegociacion() {
        return this.negociacion;
    }

    public void setNegociacion(Negociacion negociacion) {
        this.negociacion = negociacion;
    }

}