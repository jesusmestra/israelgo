/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.CargoFacade;
import com.j2mk.israelgo.model.Cargo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "cargoController")
@SessionScoped
public class CargoController implements Serializable {
    
    @Inject
    private CargoFacade cargoFacade;
    private Cargo seleccionado = new Cargo();
     
    public CargoController() {
    }


    public List<Cargo> getCargos(){
        return cargoFacade.findAll();
    }

    public Cargo getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cargo seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String editar(Cargo c){
        this.seleccionado = c;
        return "editar";
    }
    
    public String crear(){
        this.seleccionado = new Cargo();
        return "crear";
    }
    
    
    
    
    public String actualizar(){
         try {
            cargoFacade.edit(seleccionado);
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
            cargoFacade.create(seleccionado);
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
        return "/cargo/listado";
    }
    
    
}
