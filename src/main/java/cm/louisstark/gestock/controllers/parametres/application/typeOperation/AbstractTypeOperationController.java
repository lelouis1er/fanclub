/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.application.typeOperation;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.TypeOperation;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
public abstract class AbstractTypeOperationController extends SuperController {

    @Override
    public void define_create_update_delete_details(Object o) {
        try {
            if (o != null && o instanceof TypeOperation) {
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
    public void define_list_typeOperations() {
        try {
            list_typeOperations = typeOperationFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }

    

    public void prepareCreate() {

        mode = "Create";
        try {
            typeOperation = new TypeOperation();

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }

    }

    public void prepareEdit() {

        mode = "Edit";
        try {
            

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }


}
