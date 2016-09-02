/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.EstadoInmuebleFacade;
import com.j2mk.israelgo.model.EstadoInmueble;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "estadoInmuebleController")
@SessionScoped
public class EstadoInmuebleController implements Serializable {

   @Inject
    private EstadoInmuebleFacade estadoInmuebleFacade;
    private EstadoInmueble seleccionado = new EstadoInmueble();
     
    public EstadoInmuebleController() {
    }


    public List<EstadoInmueble> getEstadoInmuebles(){
        return estadoInmuebleFacade.findAll();
    }

    public EstadoInmueble getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(EstadoInmueble seleccionado) {
        this.seleccionado = seleccionado;
    }


    
    public String editar(EstadoInmueble ei){
        this.seleccionado = ei;
        return "editar";
    }
    
    public String crear(){
        System.out.println("Creando aksamskmk");
        this.seleccionado = new EstadoInmueble();
        return "crear";
    }
    
    
    
    
    public String actualizar(){
         try {
            estadoInmuebleFacade.edit(seleccionado);
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
             
            estadoInmuebleFacade.create(seleccionado);
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
        return "/estadoInmueble/listado";
    }
    
    
}
