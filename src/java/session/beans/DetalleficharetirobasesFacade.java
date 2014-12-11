/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Detalleficharetirobases;

/**
 *
 * @author Hugo Campos
 */
@Stateless
public class DetalleficharetirobasesFacade extends AbstractFacade<Detalleficharetirobases> {
    @PersistenceContext(unitName = "SIACAMRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleficharetirobasesFacade() {
        super(Detalleficharetirobases.class);
    }
    
}
