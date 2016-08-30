/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j2mk.israelgo.business;

import com.j2mk.israelgo.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jkelsy
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "IsraelGoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findByLogin(String login)  {
        List<Usuario> lista = null;

       // try {
            String jpql = " FROM Usuario u WHERE u.login = ?1";
            lista = em.createQuery(jpql, Usuario.class)
                    .setParameter(1, login).getResultList();
/*        } catch (Exception e) {
            System.err.println("ERRROROROORO" + e.getMessage());
            throw e;
        }*/
        if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }

}
