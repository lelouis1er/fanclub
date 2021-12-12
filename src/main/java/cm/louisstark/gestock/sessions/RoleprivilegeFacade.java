/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.entities.RestrictionPrivilege;
import cm.louisstark.gestock.entities.RolePrivilege;
import cm.louisstark.gestock.entities.RoleUtilisateur;
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
public class RoleprivilegeFacade extends AbstractFacade<RolePrivilege> implements RoleprivilegeFacadeLocal {

    @PersistenceContext(unitName = "cm.louisstark_geStock_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleprivilegeFacade() {
        super(RolePrivilege.class);
    }

    @Override
    public RolePrivilege findAll_by_roleutilisateur_privilegeutilisateur(RoleUtilisateur r, PrivilegesUtilisateur p) {
        Query q = em.createQuery("SELECT r FROM RolePrivilege r WHERE r.roleUtilisateur.idrole = :id_r AND r.privilegesUtilisateur.idprivilege = :id_p");
        try {
            q.setParameter("id_r", r.getIdrole());
            q.setParameter("id_p", p.getIdprivilege());
            return (RolePrivilege) q.getResultList().get(0);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<RolePrivilege> findAll_by_RoleUtilisateur(RoleUtilisateur r) {
        Query q = em.createQuery("SELECT r FROM RolePrivilege r WHERE r.roleUtilisateur.idrole = :id_r");
        q.setParameter("id_r", r.getIdrole());
        return q.getResultList();
    }

    @Override
    public int nextId() {
        Query q = em.createQuery("SELECT MAX(r.idroleprivilege) FROM RolePrivilege r");
        try {
            return (Integer) q.getResultList().get(0) + 1;
        } catch (Exception e) {
        }
        return 1;
    }
}
