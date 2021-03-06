/**
 * This file was generated by the JPA Modeler
 */
package com.j2mk.israelgo.model;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author jdmp
 */
@Entity
@Table(name = "empresa")
@NamedQuery(name = "Empresa.findByNit", query = "Select e from Empresa e WHERE e.nit =:nit")
public class Empresa implements Serializable {

    @Column(name = "emp_id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_direccion")
    @Basic
    private String direccion;

    @Column(name = "emp_nombre", unique = true)
    @Basic
    private String nombre;

    @Column(name = "emp_email_pass")
    @Basic
    private String emailPass;

    @Column(name = "emp_nit")
    @Basic
    private String nit;

    @Column(name = "emp_logo")
    @Basic
    private Blob logo;

    @Column(name = "emp_email_host")
    @Basic
    private String emailHost;

    @Column(name = "emp_telefono")
    @Basic
    private String telefono;

    @Column(name = "emp_email_port")
    @Basic
    private String emailPort;

    @Column(name = "emp_email")
    @Basic
    private String email;

    @ManyToOne(targetEntity = Tercero.class)
    @JoinColumn(name = "REPRESENTANTE_ID")
    private Tercero representante;

    @ManyToOne(targetEntity = Poblado.class)
    @JoinColumn(name = "POBLADO_ID")
    private Poblado poblado;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public String getEmailPass() {
        return this.emailPass;
    }

    public void setEmailPass(String emailPass) {
        this.emailPass = emailPass;
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Blob getLogo() {
        return this.logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    public String getEmailHost() {
        return this.emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailPort() {
        return this.emailPort;
    }

    public void setEmailPort(String emailPort) {
        this.emailPort = emailPort;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Tercero getRepresentante() {
        return this.representante;
    }

    public void setRepresentante(Tercero representante) {
        this.representante = representante;
    }

    public Poblado getPoblado() {
        return this.poblado;
    }

    public void setPoblado(Poblado poblado) {
        this.poblado = poblado;
    }

}
