/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.Municipio;
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
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }
    
    
    
    public List<Municipio> buscarMunicipio(Long departamentoId) throws Exception{
        List<Municipio> municipioList = null;
        
        try {
            String jpql = " FROM Municipio m WHERE m.departamento.id = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, departamentoId);
            municipioList = query.getResultList();
        } catch (Exception e) {
             System.err.println("ERRROROROORO"+e.getMessage());
            throw e;
        }
        return municipioList;
    }
    
}
