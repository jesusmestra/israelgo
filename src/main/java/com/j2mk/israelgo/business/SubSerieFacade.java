/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.SubSerie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jkelsy
 */
@Stateless
public class SubSerieFacade extends AbstractFacade<SubSerie> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubSerieFacade() {
        super(SubSerie.class);
    }
    
     public SubSerie findByCodigo(String codigo){
         List<SubSerie> lista =  em.createNamedQuery("SubSerie.findByCodigo", SubSerie.class)
                .setParameter("codigo", codigo)
                .getResultList();
         
          if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }
    
}
