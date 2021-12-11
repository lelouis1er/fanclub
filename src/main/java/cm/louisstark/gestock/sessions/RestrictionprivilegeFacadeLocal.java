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
import javax.ejb.Local;

/**
 *
 * @author Louis Stark
 */
@Local
public interface RestrictionprivilegeFacadeLocal {

    void create(RestrictionPrivilege restrictionprivilege);

    void edit(RestrictionPrivilege restrictionprivilege);

    void remove(RestrictionPrivilege restrictionprivilege);

    RestrictionPrivilege find(Object id);
    
    RestrictionPrivilege findAll_by_utilisateur_privilegeutilisateur(Utilisateur u, PrivilegesUtilisateur p);

    List<RestrictionPrivilege> findAll();
    
    List<RestrictionPrivilege> findAll_by_Utilisateur(Utilisateur u);

    List<RestrictionPrivilege> findRange(int[] range);

    int count();
    
    int nextId();
    
}
