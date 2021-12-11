/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.entities.Membre;
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
public class MembreFacade extends AbstractFacade<Membre> implements MembreFacadeLocal {

    @PersistenceContext(unitName = "cm.louisstark_geStock_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MembreFacade() {
        super(Membre.class);
    }

    @Override
    public List<Membre> findAllBy_societe(FanClub fc) {
        Query q = em.createQuery("SELECT m FROM Membre m WHERE m.fanClub.idFanClub = :id_f ");
        q.setParameter("id_f", fc.getIdFanclub());
        return q.getResultList();
    }

    @Override
    public Integer nextId() {
        Query q = em.createQuery("SELECT MAX(m.idMembre) FROM Membre m");
        try {
            return (Integer) q.getResultList().get(0) + 1;
        } catch (Exception e) {
        }
        return 1;
    }
    
}
