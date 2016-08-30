/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.TablaRetencionDocumental;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jkelsy
 */
@Stateless
public class TRDFacade extends AbstractFacade<TablaRetencionDocumental> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TRDFacade() {
        super(TablaRetencionDocumental.class);
    }
    
    public List<TablaRetencionDocumental> findByEmpresa(Long empresaId){
        return em.createNamedQuery("TablaRetencionDocumental.findByEmpresa", TablaRetencionDocumental.class)
                .setParameter("empresa_id", empresaId)
                .getResultList();
    }
    
}
