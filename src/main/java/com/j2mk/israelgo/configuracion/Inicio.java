/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.configuracion;

import com.j2mk.israelgo.business.EmpresaFacade;
import com.j2mk.israelgo.business.PermisoFacade;
import com.j2mk.israelgo.business.RolFacade;
import com.j2mk.israelgo.business.TerceroFacade;
import com.j2mk.israelgo.business.TipoIdentificacionFacade;
import com.j2mk.israelgo.business.UsuarioFacade;
import com.j2mk.israelgo.model.Empresa;
import com.j2mk.israelgo.model.Permiso;
import com.j2mk.israelgo.model.Rol;
import com.j2mk.israelgo.model.Tercero;
import com.j2mk.israelgo.model.TipoIdentificacion;
import com.j2mk.israelgo.model.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author jkelsy
 */
@Startup
@Singleton
public class Inicio {


    @Inject
    private UsuarioFacade usuarioFacade;

    @Inject
    private RolFacade rolFacade;

    @Inject
    private PermisoFacade permisoFacade;

    @Inject
    private TipoIdentificacionFacade tipoIdentificacionFacade;
    
    
    @Inject
    private TerceroFacade terceroFacade;
    
     @Inject
    private EmpresaFacade empresaFacade;
     
    @PostConstruct
    public void iniciar() {


        /*for (int i = 0; i <50; i++){
            cargo = new Cargo();
            cargo.setCodigo("codigo"+i);
            cargo.setDescripcion("Descripcion"+i);            
            cargoFacade.create(cargo);
        }*/
        Rol rol = rolFacade.findByNombre("ADMIN");

        if (rol == null) {
            rol = new Rol();
            rol.setNombre("ADMIN");
            rolFacade.create(rol);
        }

        Usuario usuario = usuarioFacade.findByLogin("admin");

        if (usuario == null) {
            usuario = new Usuario();
            usuario.setLogin("admin");
            usuario.setPassword("admin");
            usuarioFacade.create(usuario);
        }

        Permiso permiso = permisoFacade.findByUsuarioAndRol(usuario, rol);

        if (permiso == null) {
            permiso = new Permiso();
            permiso.setUsuario(usuario);
            permiso.setRol(rol);
            permisoFacade.create(permiso);
        }    
        
        
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionFacade.findByCodigo("01");
        
        if(tipoIdentificacion == null){
            tipoIdentificacion = new TipoIdentificacion();
            tipoIdentificacion.setAbreviatura("CC");
            tipoIdentificacion.setCodigo("01");
            tipoIdentificacion.setNombre("Cedula de Ciudadania");
            tipoIdentificacionFacade.create(tipoIdentificacion);
        }
        
        
        /* EMPRESA 2*/
        
        Tercero jefe2 = terceroFacade.findByIdentificacion("11000531");
        
         if(jefe2 == null){
            jefe2 = new Tercero();
            jefe2.setIdentificacion("11000531");
            jefe2.setNombres("Juan Manuel");
            jefe2.setApellido1("Kelsy");
            jefe2.setApellido2("Romero");
            jefe2.setDireccion("Centro");
            jefe2.setTipoIdentificacion(tipoIdentificacion);
            jefe2.setEmail("jkelsy@comfacor.com.co");
            jefe2.setTelefono("7810010");
            terceroFacade.create(jefe2);
        }
         
         
         
         Empresa empresa2 = empresaFacade.findByNit("999999988");
         
         if(empresa2 == null){
             empresa2 = new  Empresa();
             empresa2.setNit("999999988");
             empresa2.setNombre("Sistem Kelsy");
             empresa2.setDireccion("Centro");
             empresa2.setEmail("admin@sistemkelsy.com.co");
             empresa2.setEmailHost("NA");
             empresa2.setEmailPass("NA");
             empresa2.setEmailPort("80");
             //empresa.setPoblado(poblado);
             empresa2.setTelefono("7810010");
             empresa2.setRepresentante(jefe2);
             empresaFacade.create(empresa2);
         }
             
         
         /* EMPRESA 2*/
        
               
        
        
        /* EMPRESA 1*/
        
        Tercero jefe = terceroFacade.findByIdentificacion("10766753");
        
         if(jefe == null){
            jefe = new Tercero();
            jefe.setIdentificacion("10766753");
            jefe.setNombres("Jesus David");
            jefe.setApellido1("Mestra");
            jefe.setApellido2("Polo");
            jefe.setDireccion("Centro");
            jefe.setTipoIdentificacion(tipoIdentificacion);
            jefe.setEmail("jmestra@comfacor.com.co");
            jefe.setTelefono("7896979");
            terceroFacade.create(jefe);
        }
         
         
         
         Empresa empresa = empresaFacade.findByNit("999999999");
         
         if(empresa == null){
             empresa = new  Empresa();
             empresa.setNit("999999999");
             empresa.setNombre("Link Center");
             empresa.setDireccion("Centro");
             empresa.setEmail("admin@linkcenter.com.co");
             empresa.setEmailHost("NA");
             empresa.setEmailPass("NA");
             empresa.setEmailPort("80");
             empresa.setRepresentante(jefe);
             empresaFacade.create(empresa);
         }
             
         /* EMPRESA 1*/

         
         
         
         
         
         
    }
}
