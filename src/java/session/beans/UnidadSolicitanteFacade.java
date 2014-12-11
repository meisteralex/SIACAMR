/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.UnidadSolicitante;

/**
 *
 * @author Hugo Campos
 */
@Stateless
public class UnidadSolicitanteFacade extends AbstractFacade<UnidadSolicitante> {
    @PersistenceContext(unitName = "SIACAMRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnidadSolicitanteFacade() {
        super(UnidadSolicitante.class);
    }
    
}
