/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.CentroCosto;
import com.j2mk.israelgo.model.Empresa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jkelsy
 */
@Stateless
public class CentroCostoFacade extends AbstractFacade<CentroCosto> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CentroCostoFacade() {
        super(CentroCosto.class);
    }
    
     public CentroCosto findByNombre(String nombre){
         List<CentroCosto> lista = em.createNamedQuery("CentroCosto.findByNombre", CentroCosto.class)
                .setParameter("nombre", nombre)
                .getResultList();
        
        
        if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }
    
     /*
      public CentroCosto findByEmpresaAndNombre(Empresa empresa,String nombre){
         List<CentroCosto> lista = em.createNamedQuery("CentroCosto.findByEmpresaAndNombre", CentroCosto.class)
                .setParameter("empresa", empresa)
                .setParameter("nombre", nombre)
                .getResultList();
         
          if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }
    */
    
}
