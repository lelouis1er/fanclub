/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.application;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.ModelOperation;
import cm.louisstark.gestock.entities.Operation;
import cm.louisstark.gestock.entities.SessionOp;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.PrimeFaces;

/**
 *
 * @author lstark
 */
public abstract class AbstractOperationController extends SuperController {
    protected String date_op = "";
    protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
    public void define_list_membres() {
        try {
            list_membres = membreFacadeLocal.findAllBy_societe(sessionManager.get_user_enterprise());
        } catch (Exception e) {
        }
    }

    @Override
    public void define_list_modelOperations() {
        try {
            list_modelOperation = modelOperationFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }

    @Override
    public void define_list_operations() {
        try {
            list_operations = operationFacadeLocal.findAllBy_session(sessionManager.getCycleEntreprise());
                 
        } catch (Exception e) {
        }
    }
    
    
    public void prepareCreate() {
        mode = "Create";
        try {
            operation = new Operation();
            membre = new Membre(0);
            modelOperation = new ModelOperation(0);

            PrimeFaces.current().executeScript("PF('OPCreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }

    }

    public void prepareEdit() {

        mode = "Edit";
        try {
            
            membre = operation.getMembre();
            modelOperation = operation.getModelOperation();
            
            date_op = format.format(operation.getDateOperation());

            PrimeFaces.current().executeScript("PF('OPCreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public String getDate_op() {
        return date_op;
    }

    public void setDate_op(String date_op) {
        this.date_op = date_op;
    }

}
