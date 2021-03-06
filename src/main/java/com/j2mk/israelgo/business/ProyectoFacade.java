/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.Proyecto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jdmp
 */
@Stateless
public class ProyectoFacade extends AbstractFacade<Proyecto>{
        
    @PersistenceContext(unitName = "IsraelGoPU")

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoFacade() {
        super(Proyecto.class);
    }

    
    public List<Proyecto> findByEmpresa(Long empresaId){
        return em.createNamedQuery("Proyecto.findByEmpresa", Proyecto.class)
                .setParameter("empresa_id", empresaId)
                .getResultList();
    }
    
}
