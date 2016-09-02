/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.OfertaFacade;
import com.j2mk.israelgo.model.Oferta;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "ofertaController")
@SessionScoped
public class OfertaController implements Serializable {

   @Inject
    private OfertaFacade ofertaFacade;
    private Oferta seleccionado = new Oferta();
     
    public OfertaController() {
    }


    public List<Oferta> getOfertas(){
        return ofertaFacade.findAll();
    }

    public Oferta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Oferta seleccionado) {
        this.seleccionado = seleccionado;
    }


    
    public String editar(Oferta of){
        this.seleccionado = of;
        return "editar";
    }
    
    public String crear(){
        System.out.println("Creando aksamskmk");
        this.seleccionado = new Oferta();
        return "crear";
    }
    
    
    
    
    public String actualizar(){
         try {
            ofertaFacade.edit(seleccionado);
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
             
            ofertaFacade.create(seleccionado);
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
        return "/oferta/listado";
    }
    
    
}
