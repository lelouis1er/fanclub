/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.application.bilan;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.Operation;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author lstark
 */
@ManagedBean(name = "bilanController")
@ViewScoped
public class BilanController extends SuperController implements Serializable {

    @Override
    public void define_create_update_delete_details(Object o) {
        try {
            if (o != null && o instanceof Operation) {
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
    public void define_list_operations() {
        try {
                
            if (membre != null && membre.getIdmembre() != null && membre.getIdmembre() != 0) {
                
                list_operations = operationFacadeLocal.findAllBy_session_membre(sessionManager.getCycleEntreprise(), membre);
                
            }
            
            
        } catch (Exception e) {
        }
    }

    @Override
    public void define_list_membres() {
        try {
            list_membres = membreFacadeLocal.findAllBy_societe(sessionManager.get_user_enterprise());
        } catch (Exception e) {
        }
    }
    
    
    

    
    
}
