/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.Operation;
import cm.louisstark.gestock.entities.SessionOp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lstark
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> implements OperationFacadeLocal {

    @PersistenceContext(unitName = "cm.louisstark_geStock_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    }

    @Override
    public List<Operation> findAllBy_session(SessionOp cycleEntreprise) {
        Query q = em.createQuery("SELECT o FROM Operation o WHERE o.sessionOp.idSession = :id_s");
        try {
            q.setParameter("id_s", cycleEntreprise.getIdSession());
            return q.getResultList();
        } catch (Exception e) {
        }
        return new ArrayList<>();
    }

    @Override
    public long nextId() {
        Query q = em.createQuery("SELECT MAX(o.idOperation) FROM Operation o");
        try {
            return (Long) q.getResultList().get(0) + 1;
        } catch (Exception e) {
        }
        return 1l;
    }

    @Override
    public List<Operation> findAllBy_session_membre(SessionOp so, Membre membre) {
        Query q = em.createQuery("SELECT o FROM Operation o WHERE o.sessionOp.idSession = :id_s AND o.membre.idmembre = :id_m");
        try {
            q.setParameter("id_s", so.getIdSession());
            q.setParameter("id_m", membre.getIdmembre());
            return q.getResultList();
        } catch (Exception e) {
        }
        return new ArrayList<>();
        
    }
    
}
