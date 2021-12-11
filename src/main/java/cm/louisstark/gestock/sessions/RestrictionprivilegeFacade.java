/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.entities.RestrictionPrivilege;
import cm.louisstark.gestock.entities.Utilisateur;
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
public class RestrictionprivilegeFacade extends AbstractFacade<RestrictionPrivilege> implements RestrictionprivilegeFacadeLocal {

    @PersistenceContext(unitName = "cm.louisstark_geStock_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestrictionprivilegeFacade() {
        super(RestrictionPrivilege.class);
    }

    @Override
    public int nextId() {
        Query q = em.createQuery("SELECT MAX(r.idrestriction) FROM RestrictionPrivilege r");
        try {
            return (Integer) q.getResultList().get(0) + 1;
        } catch (Exception e) {
        }
        return 1;
    }

    @Override
    public List<RestrictionPrivilege> findAll_by_Utilisateur(Utilisateur u) {
        Query q = em.createQuery("SELECT r FROM RestrictionPrivilege r WHERE r.utilisateur.idUtilisateur = :id_user");
        q.setParameter("id_user", u.getIdUtilisateur());
        return q.getResultList();
    }

    @Override
    public RestrictionPrivilege findAll_by_utilisateur_privilegeutilisateur(Utilisateur u, PrivilegesUtilisateur p) {
        Query q = em.createQuery("SELECT r FROM RestrictionPrivilege r WHERE r.utilisateur.idUtilisateur = :id_user AND r.privilegesUtilisateur.idprivilege = :id_p");
        try {
            q.setParameter("id_user", u.getIdUtilisateur());
            q.setParameter("id_p", p.getIdprivilege());

            return (RestrictionPrivilege) q.getResultList().get(0);
        } catch (Exception e) {
        }
        return null;
    }
}
