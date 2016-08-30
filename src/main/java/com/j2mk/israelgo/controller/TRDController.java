/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.CargoFacade;
import com.j2mk.israelgo.business.EmpresaFacade;
import com.j2mk.israelgo.business.TRDFacade;
import com.j2mk.israelgo.model.Cargo;
import com.j2mk.israelgo.model.Empresa;
import com.j2mk.israelgo.model.TablaRetencionDocumental;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "trdController")
@SessionScoped
public class TRDController implements Serializable {
    
    private Empresa empresa;
    
    @Inject
    private TRDFacade trdf;
    
    @Inject 
    private EmpresaFacade empresaFacade;
    
    private TablaRetencionDocumental seleccionado = new TablaRetencionDocumental();
     
    private List<TablaRetencionDocumental> trds;
    
    private Long empresaId;
    
    
    public TRDController() {
    }  
    
    public List<TablaRetencionDocumental> getTRD() {
        return trdf.findAll();
    }
    
    public void actualizarTRDPorEmpresa() {
        //System.out.println("Empresa"+empresa);
        trds =  trdf.findByEmpresa(empresaId);
    }
    
    public List<Empresa> getEmpresas(){
        return empresaFacade.findAll();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TablaRetencionDocumental getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(TablaRetencionDocumental seleccionado) {
        this.seleccionado = seleccionado;
    }

    public List<TablaRetencionDocumental> getTrds() {
        return trds;
    }

    public void setTrds(List<TablaRetencionDocumental> trds) {
        this.trds = trds;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }
    
    
    
}
