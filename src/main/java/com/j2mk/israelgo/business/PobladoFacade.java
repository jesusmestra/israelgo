/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.Poblado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jkelsy
 */
@Stateless
public class PobladoFacade extends AbstractFacade<Poblado> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PobladoFacade() {
        super(Poblado.class);
    }
    
    
    
    public List<Poblado> buscarPoblado(Long municipioId) throws Exception{
        List<Poblado> pobladoList = null;
        
        try {
            String jpql = " FROM Poblado m WHERE m.municipio.id = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, municipioId);
            pobladoList = query.getResultList();
        } catch (Exception e) {
             System.err.println("ERRROROROORO"+e.getMessage());
            throw e;
        }
        
        return pobladoList;
    }
    
}
