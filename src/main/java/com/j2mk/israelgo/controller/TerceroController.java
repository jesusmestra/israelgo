/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.TerceroFacade;
import com.j2mk.israelgo.business.TipoIdentificacionFacade;
import com.j2mk.israelgo.model.Tercero;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "terceroController")
@SessionScoped
public class TerceroController implements Serializable {
    
    @Inject
    private TerceroFacade terceroFacade;
    private Tercero seleccionado = new Tercero();
     
    private Long tipoIndetificacionId;
    
    @Inject
    private TipoIdentificacionFacade tipoIdentificacionFacade;
    
    public TerceroController() {
    }


    public List<Tercero> getTerceros(){
        return terceroFacade.findAll();
    }

    public Tercero getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Tercero seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Long getTipoIndetificacionId() {
        return tipoIndetificacionId;
    }

    public void setTipoIndetificacionId(Long tipoIndetificacionId) {
        this.tipoIndetificacionId = tipoIndetificacionId;
    }


    public String editar(Tercero t){
        this.seleccionado = t;
        this.tipoIndetificacionId = this.seleccionado.getTipoIdentificacion().getId();
        return "editar";
    }
    
    public String crear(){
        this.seleccionado = new Tercero();
        return "crear";
    }
    
    
    
    
    public String actualizar(){
         try {
             seleccionado.setTipoIdentificacion(tipoIdentificacionFacade.find(tipoIndetificacionId));
            terceroFacade.edit(seleccionado);
            // MENSAJE
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Actualizado!"));
        } catch (Exception e) {
            // MENSAJE
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        }finally{
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
         }
         
        return "editar";
    }
    
    public String grabar(){
        
         try {
             seleccionado.setTipoIdentificacion(tipoIdentificacionFacade.find(tipoIndetificacionId));
            terceroFacade.create(seleccionado);
            // MENSAJE
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Creado!"));
        } catch (Exception e) {
            // MENSAJE
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        }finally{
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
         }
         
        setSeleccionado(seleccionado);
        return "editar";
    }
  
    
    public String listado(){
        return "/tercero/listado";
    }
    
    
}
