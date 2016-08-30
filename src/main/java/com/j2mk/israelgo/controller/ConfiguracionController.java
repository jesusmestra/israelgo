/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.controller;

import com.j2mk.israelgo.business.ConfiguracionFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named(value = "configuracionController")
@SessionScoped
public class ConfiguracionController implements Serializable {
    
    @Inject
    ConfiguracionFacade configuracion;

    private Part divipola;

    public ConfiguracionController() {
    }

    public Part getDivipola() {
        return divipola;
    }

    public void setDivipola(Part divipola) {
        this.divipola = divipola;
    }

    public void cargarDivipola() {       
        configuracion.cargarDivipola(divipola);
    }

}
