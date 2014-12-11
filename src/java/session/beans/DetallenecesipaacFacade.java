/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Detallenecesipaac;

/**
 *
 * @author Hugo Campos
 */
@Stateless
public class DetallenecesipaacFacade extends AbstractFacade<Detallenecesipaac> {
    @PersistenceContext(unitName = "SIACAMRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallenecesipaacFacade() {
        super(Detallenecesipaac.class);
    }
    
}
