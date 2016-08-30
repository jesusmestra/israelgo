/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.Empresa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }

     public Empresa findByNit(String nit){
        List<Empresa> lista = em.createNamedQuery("Empresa.findByNit", Empresa.class)
                .setParameter("nit", nit)
                .getResultList();
        
        
        if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }
    
  
    
}
