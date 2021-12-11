/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.security.privileges;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.Menu;
import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
public abstract class AbstractPrivilegesController extends SuperController {

    @Override
    public void define_create_update_delete_details(Object o) {
        try {
            if (o != null && o instanceof PrivilegesUtilisateur) {
                modifier = sessionManager.user_can_update();
                supprimer = sessionManager.user_can_delete();
            } else {
                modifier = supprimer = false;
            }
        } catch (Exception e) {
            modifier = supprimer = details = false;
        }
    }

    @Override
    public void define_list_menus() {
        try {
            list_menus = menuFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }

    @Override
    public void define_list_privilegesutilisateurs() {
        try {
            list_privilegesutilisateurs = privilegesutilisateurFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }

    public void prepareCreate() {

        mode = "Create";
        try {
            privilegesutilisateur = new PrivilegesUtilisateur();
            menu = new Menu(0);

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }

    }

    public void prepareEdit() {

        mode = "Edit";
        try {
            if (privilegesutilisateur.getMenu() != null) {
                menu = privilegesutilisateur.getMenu();
            } else {
                menu = new Menu(0);
            }

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
