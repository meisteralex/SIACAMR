/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Expedienteproceso;

/**
 *
 * @author Hugo Campos
 */
@Stateless
public class ExpedienteprocesoFacade extends AbstractFacade<Expedienteproceso> {
    @PersistenceContext(unitName = "SIACAMRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExpedienteprocesoFacade() {
        super(Expedienteproceso.class);
    }
    
}
