/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.ModelOperation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lstark
 */
@Stateless
public class ModelOperationFacade extends AbstractFacade<ModelOperation> implements ModelOperationFacadeLocal {

    @PersistenceContext(unitName = "cm.louisstark_geStock_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModelOperationFacade() {
        super(ModelOperation.class);
    }

    @Override
    public Integer nextId() {
        Query q = em.createQuery("SELECT MAX(m.idModel) FROM ModelOperation m");
        try {
            return (Integer) q.getResultList().get(0) +1;
        } catch (Exception e) {
        }
        return 1;
    }
    
}
