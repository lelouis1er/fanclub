/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.RoleUtilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Louis Stark
 */
@Local
public interface RoleutilisateurFacadeLocal {

    void create(RoleUtilisateur roleutilisateur);

    void edit(RoleUtilisateur roleutilisateur);

    void remove(RoleUtilisateur roleutilisateur);

    RoleUtilisateur find(Object id);

    List<RoleUtilisateur> findAll();

    List<RoleUtilisateur> findRange(int[] range);

    int count();
    
    int nextId();
    
}
