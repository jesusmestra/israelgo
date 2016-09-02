/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.EmpresaFacade;
import com.j2mk.israelgo.business.EstadoInmuebleFacade;
import com.j2mk.israelgo.business.InmuebleFacade;
import com.j2mk.israelgo.business.ProyectoFacade;
import com.j2mk.israelgo.model.Empresa;
import com.j2mk.israelgo.model.EstadoInmueble;
import com.j2mk.israelgo.model.Inmueble;
import com.j2mk.israelgo.model.Proyecto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "proyectoController")
@SessionScoped
public class ProyectoController implements Serializable {

    @Inject
    private ProyectoFacade proyectoFacade;

    @Inject
    private EmpresaFacade empresaFacade;

    @Inject
    private InmuebleFacade inmuebleFacade;

    @Inject
    private EstadoInmuebleFacade estadoInmuebleFacade;

    private Proyecto seleccionado = new Proyecto();

    private List<Proyecto> proyectos;

    private String accion;

    private List<EstadoInmueble> estadoInmuebleList;

    public List<EstadoInmueble> getEstadoInmuebleList() {
        return estadoInmuebleList;
    }

    public void setEstadoInmuebleList(List<EstadoInmueble> estadoInmuebleList) {
        this.estadoInmuebleList = estadoInmuebleList;
    }

    @PostConstruct
    public void init() {
        //user = new User();
        estadoInmuebleList = estadoInmuebleFacade.findAll();

    }


    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    private Inmueble inmuebleInstance;
    
    private Long estadoInmuebleId;

    public Long getEstadoInmuebleId() {
        return estadoInmuebleId;
    }

    public void setEstadoInmuebleId(Long estadoInmuebleId) {
        this.estadoInmuebleId = estadoInmuebleId;
    }
    

    public List<Proyecto> getProyectos() {
        List<Empresa> em;

        em = empresaFacade.findAll();
        if (empresaId == null) {
            if (em != null) {
                empresaId = em.get(0).getId();
            }
        }

        proyectos = proyectoFacade.findByEmpresa(empresaId);
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    private Long empresaId;

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public ProyectoController() {
    }

    public List<Empresa> getEmpresas() {
        return empresaFacade.findAll();
    }

    public Proyecto getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Proyecto seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String editar(Proyecto pr) {

        this.seleccionado = pr;

        if (this.seleccionado.getEmpresa() != null) {
            this.empresaId = this.seleccionado.getEmpresa().getId();

        }

        return "editar";
    }

    public String crear() {
        this.seleccionado = new Proyecto();
        return "crear";
    }

    public String actualizar() {
        try {
            seleccionado.setEmpresa(empresaFacade.find(empresaId));
            proyectoFacade.edit(seleccionado);
            // MENSAJE
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Actualizado!"));
        } catch (Exception e) {
            // MENSAJE
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }

        return "editar";
    }

    public String grabar() {
        try {
            seleccionado.setEmpresa(empresaFacade.find(empresaId));
            proyectoFacade.create(seleccionado);
            // MENSAJE
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Creado!"));
        } catch (Exception e) {
            // MENSAJE
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }

        setSeleccionado(seleccionado);
        return "editar";
    }

    public String listado() {
        return "/proyecto/listado";
    }

    public void actualizarProyectoPorEmpresa() {
        System.out.println("Empresa" + empresaId);
        proyectos = proyectoFacade.findByEmpresa(empresaId);
    }

    public String mostrar(Proyecto proy) throws Exception {
        this.seleccionado = proy;
        this.estadoInmuebleList = estadoInmuebleFacade.findAll();

        try {
            //cargoList = empresaFacade.buscarCargos(this.seleccionado.getId());
            //centroCostoList = empresaFacade.buscarCentroCostos(this.seleccionado.getId());
            //proyectoList = empresaFacade.buscarProyectos(this.seleccionado.getId());
        } catch (Exception e) {
            throw e;
        }

        return "mostrar";

    }

    public List<Inmueble> getInmuebles(Proyecto pro) {
        return inmuebleFacade.findByProyecto(pro.getId());
    }

    public void crearInmueble(String accion) {
        this.accion = "Grabar";
        this.setEstadoInmuebleId(null);
        this.inmuebleInstance = new Inmueble();
    }

    public Inmueble getInmuebleInstance() {
        return inmuebleInstance;
    }

    public void setInmuebleInstance(Inmueble inmuebleInstance) {
        this.inmuebleInstance = inmuebleInstance;
    }

    public void registraInmueble() throws Exception {
        try {
            inmuebleInstance.setProyecto(seleccionado);
            inmuebleInstance.setEstadoInmueble(estadoInmuebleFacade.find(estadoInmuebleId));

            if (accion.equals("Grabar")) {
                inmuebleFacade.create(inmuebleInstance);
            } else if (accion.equals("Actualizar")) {
                inmuebleFacade.edit(inmuebleInstance);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            //inmuebleList = inmuebleFacade.findByProyecto(seleccionado.getId());
        }

    }

    public void seleccionarInmueble(Inmueble inm) {
        this.accion = "Actualizar";
        this.inmuebleInstance = inm;
        this.setEstadoInmuebleId(inmuebleInstance.getEstadoInmueble().getId());
    }

}
