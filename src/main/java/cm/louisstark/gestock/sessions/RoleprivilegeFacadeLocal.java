/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.entities.RolePrivilege;
import cm.louisstark.gestock.entities.RoleUtilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Louis Stark
 */
@Local
public interface RoleprivilegeFacadeLocal {

    void create(RolePrivilege roleprivilege);

    void edit(RolePrivilege roleprivilege);

    void remove(RolePrivilege roleprivilege);

    RolePrivilege find(Object id);
    
    RolePrivilege findAll_by_roleutilisateur_privilegeutilisateur(RoleUtilisateur r, PrivilegesUtilisateur p);

    List<RolePrivilege> findAll();
    
    List<RolePrivilege> findAll_by_RoleUtilisateur(RoleUtilisateur r);

    List<RolePrivilege> findRange(int[] range);

    int count();
    
    int nextId();
    
}
