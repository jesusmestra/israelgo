/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.CargoFacade;
import com.j2mk.israelgo.business.CentroCostoFacade;
import com.j2mk.israelgo.business.DepartamentoFacade;
import com.j2mk.israelgo.business.EmpresaFacade;
import com.j2mk.israelgo.business.MunicipioFacade;
import com.j2mk.israelgo.business.PobladoFacade;
import com.j2mk.israelgo.business.TerceroFacade;
import com.j2mk.israelgo.model.Cargo;
import com.j2mk.israelgo.model.CentroCosto;
import com.j2mk.israelgo.model.Departamento;
import com.j2mk.israelgo.model.Empresa;
import com.j2mk.israelgo.model.Municipio;
import com.j2mk.israelgo.model.Poblado;
import com.j2mk.israelgo.model.Tercero;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "empresaController")
@SessionScoped
public class EmpresaController implements Serializable {

    @Inject
    private EmpresaFacade empresaFacade;

    @Inject
    private TerceroFacade terceroFacade;

    @Inject
    private DepartamentoFacade departamentoFacade;

    @Inject
    private MunicipioFacade municipioFacade;

    @Inject
    private PobladoFacade pobladoFacade;

    @Inject
    private CargoFacade cargoFacade;

    @Inject
    private CentroCostoFacade centroCostoFacade;

    private Empresa seleccionado = new Empresa();

    private Long representanteId;

    private Long departamentoId;

    private Long municipioId;

    private Long pobladoId;

    private Long jefeId;

    private List<Departamento> departamentoList;

    private List<Tercero> representanteList;

    private List<Municipio> municipioList;

    private List<Poblado> pobladoList;

    private List<Cargo> cargoList;

    private List<CentroCosto> centroCostoList;

    private List<Tercero> jefeList;

    private Cargo cargoInstance;

    private CentroCosto centroCostoInstance;

    private String accion;

    private BufferedReader reader;

    public EmpresaController() {
    }

    public List<Empresa> getEmpresas() {
        return empresaFacade.findAll();
    }

    public Empresa getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Empresa seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Long getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(Long representanteId) {
        this.representanteId = representanteId;
    }

    public List<Tercero> getRepresentanteList() {
        return representanteList;
    }

    public void setRepresentanteList(List<Tercero> representanteList) {
        this.representanteList = representanteList;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }

    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    public List<Poblado> getPobladoList() {
        return pobladoList;
    }

    public void setPobladoList(List<Poblado> pobladoList) {
        this.pobladoList = pobladoList;
    }

    public Long getPobladoId() {
        return pobladoId;
    }

    public void setPobladoId(Long pobladoId) {
        this.pobladoId = pobladoId;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    public Cargo getCargoInstance() {
        return cargoInstance;
    }

    public void setCargoInstance(Cargo cargoInstance) {
        this.cargoInstance = cargoInstance;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public CentroCosto getCentroCostoInstance() {
        return centroCostoInstance;
    }

    public void setCentroCostoInstance(CentroCosto centroCostoInstance) {
        this.centroCostoInstance = centroCostoInstance;
    }

    public List<CentroCosto> getCentroCostoList() {
        return centroCostoList;
    }

    public void setCentroCostoList(List<CentroCosto> centroCostoList) {
        this.centroCostoList = centroCostoList;
    }

    public Long getJefeId() {
        return jefeId;
    }

    public void setJefeId(Long jefeId) {
        this.jefeId = jefeId;
    }

    public List<Tercero> getJefeList() {
        return jefeList;
    }

    public void setJefeList(List<Tercero> jefeList) {
        this.jefeList = jefeList;
    }

    public String editar(Empresa c) {
        departamentoList = departamentoFacade.findAll();
        representanteList = terceroFacade.findAll();

        this.seleccionado = c;

        if (this.seleccionado.getRepresentante() != null) {
            this.representanteId = this.seleccionado.getRepresentante().getId();
            this.departamentoId = this.seleccionado.getPoblado().getMunicipio().getDepartamento().getId();
            try {
                buscarMunicipio();
            } catch (Exception e) {
            }

            this.municipioId = this.seleccionado.getPoblado().getMunicipio().getId();
            try {
                buscarPoblado();
            } catch (Exception e) {
            }

        }

        return "editar";
    }

    public String crear() {
        representanteList = terceroFacade.findAll();
        departamentoList = departamentoFacade.findAll();

        this.seleccionado = new Empresa();
        return "crear";
    }

    public String actualizar() {
        try {
            seleccionado.setPoblado(pobladoFacade.find(pobladoId));
            seleccionado.setRepresentante(terceroFacade.find(representanteId));
            empresaFacade.edit(seleccionado);
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
            seleccionado.setPoblado(pobladoFacade.find(pobladoId));
            seleccionado.setRepresentante(terceroFacade.find(representanteId));
            empresaFacade.create(seleccionado);
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
        return "/secured/empresa/listado";
    }

    public void buscarMunicipio() throws Exception {
        try {
            municipioId = null;
            pobladoId = null;
            municipioList = municipioFacade.buscarMunicipio(departamentoId);

            if (municipioList != null) {
                municipioId = municipioList.get(0).getId();
                pobladoList = null;
                buscarPoblado();

            }

            if (pobladoList != null) {
                pobladoId = pobladoList.get(0).getId();
            }

        } catch (Exception e) {
            throw e;
        }

    }

    public void buscarPoblado() throws Exception {
        try {
            pobladoList = pobladoFacade.buscarPoblado(municipioId);
            if (pobladoList != null) {
                pobladoId = pobladoList.get(0).getId();
            }

        } catch (Exception e) {
            throw e;
        }

    }

    public String mostrar(Empresa emp) throws Exception {
        this.seleccionado = emp;

        try {
            cargoList = empresaFacade.buscarCargos(this.seleccionado.getId());
            centroCostoList = empresaFacade.buscarCentroCostos(this.seleccionado.getId());
        } catch (Exception e) {
            throw e;
        }

        return "mostrar";

    }

    public void registraCargo() throws Exception {

        try {
            cargoInstance.setEmpresa(seleccionado);

            if (accion.equals("Grabar")) {
                cargoFacade.create(cargoInstance);
            } else if (accion.equals("Actualizar")) {
                cargoFacade.edit(cargoInstance);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            cargoList = empresaFacade.buscarCargos(seleccionado.getId());
        }

    }

    public void crearCargo() {
        this.accion = "Grabar";
        this.cargoInstance = new Cargo();
    }

    public void seleccionarCargo(Cargo car) {
        this.accion = "Actualizar";
        this.cargoInstance = car;
    }

    public void eliminarCargo() {
        try {
            cargoFacade.remove(cargoInstance);
            cargoList = empresaFacade.buscarCargos(this.seleccionado.getId());

        } catch (Exception e) {

        } finally {

        }
    }

    public void crearCentroCosto() {
        this.accion = "Grabar";
        this.centroCostoInstance = new CentroCosto();
        this.jefeList = terceroFacade.findAll();
    }

    public void seleccionarCentroCosto(CentroCosto centroCosto) {
        this.accion = "Actualizar";
        this.centroCostoInstance = centroCosto;
        if (this.centroCostoInstance.getJefe() != null) {
            this.jefeId = this.centroCostoInstance.getJefe().getId();
        }
        this.jefeList = terceroFacade.findAll();
    }

    public void registraCentroCosto() throws Exception {

        try {
            centroCostoInstance.setEmpresa(seleccionado);
            centroCostoInstance.setJefe(terceroFacade.find(jefeId));

            if (accion.equals("Grabar")) {
                centroCostoFacade.create(centroCostoInstance);
            } else if (accion.equals("Actualizar")) {
                centroCostoFacade.edit(centroCostoInstance);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            centroCostoList = empresaFacade.buscarCentroCostos(seleccionado.getId());
        }

    }

    public void eliminarCentroCosto() {

        try {
            centroCostoFacade.remove(centroCostoInstance);
            this.jefeId = null;
            centroCostoList = empresaFacade.buscarCentroCostos(seleccionado.getId());

        } catch (Exception e) {
            System.err.println("Error" + e);
        } finally {

        }
    }

}
