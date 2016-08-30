/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.TipoIdentificacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jkelsy
 */
@Stateless
public class TipoIdentificacionFacade extends AbstractFacade<TipoIdentificacion> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoIdentificacionFacade() {
        super(TipoIdentificacion.class);
    }
    
    
            
    public TipoIdentificacion findByCodigo(String codigo){
        List<TipoIdentificacion> lista =  em.createNamedQuery("TipoIdentificacion.findByCodigo", TipoIdentificacion.class)
                .setParameter("codigo", codigo)
                .getResultList();
        
         if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }
    
}
