/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Louis Stark
 */
@Local
public interface PrivilegesutilisateurFacadeLocal {

    void create(PrivilegesUtilisateur privilegesUtilisateur);

    void edit(PrivilegesUtilisateur privilegesUtilisateur);

    void remove(PrivilegesUtilisateur privilegesUtilisateur);

    PrivilegesUtilisateur find(Object id);

    List<PrivilegesUtilisateur> findAll();

    List<PrivilegesUtilisateur> findRange(int[] range);

    int count();
    
    int nextId();
    
}
