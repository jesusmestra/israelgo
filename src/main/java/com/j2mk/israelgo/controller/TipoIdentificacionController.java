/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.TipoIdentificacionFacade;
import com.j2mk.israelgo.model.TipoIdentificacion;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "tipoIdentificacionController")
@SessionScoped
public class TipoIdentificacionController implements Serializable {
    
    @Inject
    private TipoIdentificacionFacade tipoIdentificacionFacade;
    private TipoIdentificacion seleccionado = new TipoIdentificacion();
     
    public TipoIdentificacionController() {
    }


    public List<TipoIdentificacion> getTipoIdentificaciones(){
        return tipoIdentificacionFacade.findAll();
    }

    public TipoIdentificacion getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(TipoIdentificacion seleccionado) {
        this.seleccionado = seleccionado;
    }


    
    public String editar(TipoIdentificacion ti){
        this.seleccionado = ti;
        return "editar";
    }
    
    public String crear(){
        this.seleccionado = new TipoIdentificacion();
        return "crear";
    }
    
    
    
    
    public String actualizar(){
         try {
            tipoIdentificacionFacade.edit(seleccionado);
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
            tipoIdentificacionFacade.create(seleccionado);
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
        return "/tipoIdentificacion/listado";
    }
    
    
}
