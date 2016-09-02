/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.Inmueble;
import com.j2mk.israelgo.model.Proyecto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jkelsy
 */
@Stateless
public class InmuebleFacade extends AbstractFacade<Inmueble> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InmuebleFacade() {
        super(Inmueble.class);
    }
    
    
    
    
    public List<Inmueble> findByProyecto(Long proyectoId)  {
         List<Inmueble> lista =  em.createNamedQuery("Inmueble.findByProyecto", Inmueble.class)
                .setParameter("proyecto_id", proyectoId)
                .getResultList();
        
         if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista;
    }

    public List<Inmueble> findByProyectoAndEstado(Long proyectoId, Long estadoId)  {
         List<Inmueble> lista =  em.createNamedQuery("Inmueble.findByProyectoAndEstado", Inmueble.class)
                .setParameter("proyecto_id", proyectoId)
                 .setParameter("estado_id", estadoId)
                .getResultList();
        
         if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista;
    }


}
