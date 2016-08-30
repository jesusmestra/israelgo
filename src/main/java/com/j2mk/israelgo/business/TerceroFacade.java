/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.Tercero;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TerceroFacade extends AbstractFacade<Tercero> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TerceroFacade() {
        super(Tercero.class);
    }
    
    
    public List<Tercero> buscar(String nombres) throws Exception{
        
        List<Tercero> lista; 
        try {
            String jpql = "FROM Tercero t WHERE t.nombres = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1,nombres);
            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("ERRROROROORO"+e.getMessage());
            throw e;
        }
        return lista;
    }    
    
    
     public Tercero findByIdentificacion(String identificacion){
        
         List<Tercero> lista =  em.createNamedQuery("Tercero.findByIdentificacion", Tercero.class)
                .setParameter("identificacion", identificacion)
                .getResultList();
        
         if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
        
    }
    
}
