/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.configuracion;

import com.j2mk.israelgo.business.CargoFacade;
import com.j2mk.israelgo.business.CentroCostoFacade;
import com.j2mk.israelgo.business.EmpresaFacade;
import com.j2mk.israelgo.business.PermisoFacade;
import com.j2mk.israelgo.business.RolFacade;
import com.j2mk.israelgo.business.SerieFacade;
import com.j2mk.israelgo.business.SubSerieFacade;
import com.j2mk.israelgo.business.TRDFacade;
import com.j2mk.israelgo.business.TerceroFacade;
import com.j2mk.israelgo.business.TipoIdentificacionFacade;
import com.j2mk.israelgo.business.UsuarioFacade;
import com.j2mk.israelgo.model.Cargo;
import com.j2mk.israelgo.model.CentroCosto;
import com.j2mk.israelgo.model.Empresa;
import com.j2mk.israelgo.model.Permiso;
import com.j2mk.israelgo.model.Rol;
import com.j2mk.israelgo.model.Serie;
import com.j2mk.israelgo.model.SubSerie;
import com.j2mk.israelgo.model.TablaRetencionDocumental;
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
    private CargoFacade cargoFacade;

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
    private CentroCostoFacade centroCostoFacade;
    
     @Inject
    private EmpresaFacade empresaFacade;
     
      @Inject
    private SerieFacade serieFacade;
      
       @Inject
    private SubSerieFacade subSerieFacade;
       
       @Inject
    private TRDFacade trdFacade;

    
    @PostConstruct
    public void iniciar() {

        Cargo cargo;

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
             empresa2.setArchivoRuta("NA");
             empresa2.setDireccion("Centro");
             empresa2.setEmail("admin@sistemkelsy.com.co");
             empresa2.setEmailHost("NA");
             empresa2.setEmailPass("NA");
             empresa2.setEmailPort("80");
             empresa2.setLogoFondoCarta("NA");
             empresa2.setLogoUrl("NA");
             //empresa.setPoblado(poblado);
             empresa2.setRadicadoEntrada("NA");
             empresa2.setRadicadoInterno("NA");
             empresa2.setRadicadoSalida("");
             empresa2.setTelefono("7810010");
             empresa2.setRepresentante(jefe2);
             empresaFacade.create(empresa2);
         }
             
         //CentroCosto centroCosto2 = centroCostoFacade.findByEmpresaAndNombre(empresa2,  "Contabilidad");
         CentroCosto centroCosto2 = centroCostoFacade.findByNombre("Contabilidad");
         if (centroCosto2 == null){
             centroCosto2 = new CentroCosto();
             centroCosto2.setCodigo("002");
             centroCosto2.setNombre("Contabilidad");
             centroCosto2.setConsecutivoExterno(0);
             centroCosto2.setConsecutivoInterno(0);
             centroCosto2.setEmpresa(empresa2);
             centroCosto2.setJefe(jefe2);
             centroCostoFacade.create(centroCosto2);
                     
         }
         
         Serie serie3 = serieFacade.findByCodigo("003");
         
         if(serie3 == null){
             serie3 = new Serie();
             serie3.setCodigo("003");
             serie3.setDescripcion("Tercera Serie");
             serieFacade.create(serie3);
         }
        
         SubSerie subSerie3 = subSerieFacade.findByCodigo("003");
         
         if(subSerie3 == null){
             subSerie3 = new SubSerie();
             subSerie3.setCodigo("005");
             subSerie3.setDescripcion("Tercera Sub Serie");
             subSerie3.setSerie(serie3);
             subSerieFacade.create(subSerie3);
         }
         
         
         TablaRetencionDocumental trd2 = new TablaRetencionDocumental();
         
         List<TablaRetencionDocumental> lista2 = trdFacade.findAll();
         
         trd2.setCentroCosto(centroCosto2);
         trd2.setDescrpcion("Mi seguntada TRD"+lista2.size());
         trd2.setEnDigital(Boolean.TRUE);
         trd2.setEnFisico(Boolean.FALSE);
         trd2.setEnMicro(Boolean.TRUE);
         trd2.setSeConserva(Boolean.FALSE);
         trd2.setSeElimina(Boolean.FALSE);
         trd2.setSeleccion(Boolean.TRUE);
         trd2.setSubSerie(subSerie3);
         trdFacade.create(trd2);
         
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
             empresa.setArchivoRuta("NA");
             empresa.setDireccion("Centro");
             empresa.setEmail("admin@linkcenter.com.co");
             empresa.setEmailHost("NA");
             empresa.setEmailPass("NA");
             empresa.setEmailPort("80");
             empresa.setLogoFondoCarta("NA");
             empresa.setLogoUrl("NA");
             //empresa.setPoblado(poblado);
             empresa.setRadicadoEntrada("NA");
             empresa.setRadicadoInterno("NA");
             empresa.setRadicadoSalida("");
             empresa.setTelefono("7896979");
             empresa.setRepresentante(jefe);
             empresaFacade.create(empresa);
         }
             
         //CentroCosto centroCosto = centroCostoFacade.findByEmpresaAndNombre(empresa,  "Sistemas");
         CentroCosto centroCosto = centroCostoFacade.findByNombre("Sistemas");
         if (centroCosto == null){
             centroCosto = new CentroCosto();
             centroCosto.setCodigo("001");
             centroCosto.setNombre("Sistemas");
             centroCosto.setConsecutivoExterno(0);
             centroCosto.setConsecutivoInterno(0);
             centroCosto.setEmpresa(empresa);
             centroCosto.setJefe(jefe);
             centroCostoFacade.create(centroCosto);
                     
         }
         
         Serie serie = serieFacade.findByCodigo("001");
         
         if(serie == null){
             serie = new Serie();
             serie.setCodigo("001");
             serie.setDescripcion("Primera Serie");
             serieFacade.create(serie);
         }
        
         SubSerie subSerie = subSerieFacade.findByCodigo("001");
         
         if(subSerie == null){
             subSerie = new SubSerie();
             subSerie.setCodigo("001");
             subSerie.setDescripcion("Primera Serie");
             subSerie.setSerie(serie);
             subSerieFacade.create(subSerie);
         }
         
         
         TablaRetencionDocumental trd = new TablaRetencionDocumental();
         
         List<TablaRetencionDocumental> lista = trdFacade.findAll();
         
         trd.setCentroCosto(centroCosto);
         trd.setDescrpcion("Mi primer TRD"+lista.size());
         trd.setEnDigital(Boolean.TRUE);
         trd.setEnFisico(Boolean.FALSE);
         trd.setEnMicro(Boolean.TRUE);
         trd.setSeConserva(Boolean.FALSE);
         trd.setSeElimina(Boolean.FALSE);
         trd.setSeleccion(Boolean.TRUE);
         trd.setSubSerie(subSerie);
         trdFacade.create(trd);
         
         /* EMPRESA 1*/

         
         
         
         
         
         
    }
}
