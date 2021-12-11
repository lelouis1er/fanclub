/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.entities.SessionOp;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Louis Stark
 */
@Stateless
public class SessionOpFacade extends AbstractFacade<SessionOp> implements SessionOpFacadeLocal {

    @PersistenceContext(unitName = "cm.louisstark_geStock_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionOpFacade() {
        super(SessionOp.class);
    }

    @Override
    public List<SessionOp> findAllBy_societe(FanClub f) {
        Query q = em.createQuery("SELECT s FROM SessionOp s WHERE s.fanClub.idFanclub = :id_s ORDER BY s.dateDebut");
        q.setParameter("id_s", f.getIdFanclub());
        return q.getResultList();
    }

    @Override
    public int nextId() {
        Query q = em.createQuery("SELECT MAX(s.idSession) FROM SessionOp s");
        try {
            return (Integer) q.getResultList().get(0) + 1;
        } catch (Exception e) {
        }
        return 1;
    }
    
}
