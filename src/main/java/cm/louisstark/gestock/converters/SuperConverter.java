/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.converters;

import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.sessions.PrivilegesutilisateurFacadeLocal;
import cm.louisstark.gestock.utilitaires.SessionManagerImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

/**
 *
 * @author Louis Stark
 */
@ManagedBean
@Named(value = "superConverter")
public class SuperConverter {
    
    @ManagedProperty (value = "#{manageBean}")
    protected SessionManagerImpl sessionManager;

    @EJB
    protected PrivilegesutilisateurFacadeLocal privilegesutilisateurFacadeLocal;
    protected List<PrivilegesUtilisateur> list_privilegesutilisateur = new ArrayList<>();
    

    public void setSessionManager(SessionManagerImpl sessionManager) {
        this.sessionManager = sessionManager;
    }

    @PostConstruct
    private void init() {
        list_privilegesutilisateur = privilegesutilisateurFacadeLocal.findAll();
    }

    public SuperConverter() {
    }

    public PrivilegesUtilisateur find_privilegeInList(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Pas d'id fournie");
        }
        for (PrivilegesUtilisateur item : list_privilegesutilisateur) {
            if (item.getIdprivilege().equals(id)) {
                return item;
            }
        }
        return null;
    }

}
