/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgd.hermes.configuracion;

import com.sgd.hermes.business.CargoFacade;
import com.sgd.hermes.business.PermisoFacade;
import com.sgd.hermes.business.RolFacade;
import com.sgd.hermes.business.UsuarioFacade;
import com.sgd.hermes.model.Cargo;
import com.sgd.hermes.model.Permiso;
import com.sgd.hermes.model.Rol;
import com.sgd.hermes.model.Usuario;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private BufferedReader reader;

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

        System.out.println("ajshkjashkj");

    }

    public void cargarDepartamento() {
        reader = new BufferedReader(
                new InputStreamReader(
                        this
                        .getClass()
                        .getClassLoader()
                        .getResourceAsStream("/META-INF/departamento.csv")
                )
        );

        
        //String csvFile =  path.toString();//  "/resources/departamento.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = reader;//new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] departamento = line.split(cvsSplitBy);

                System.out.println("Departamento [codigo= " + departamento[0] + " , nombre=" + departamento[1] + "]");

            }

        } catch (FileNotFoundException e) {
            System.err.println("Error" + e);

        } catch (IOException e) {
            System.err.println("Error" + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error" + e);
                }
            }
        }
        
        
    }

}
