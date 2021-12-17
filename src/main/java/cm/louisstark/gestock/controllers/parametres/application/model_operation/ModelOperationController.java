/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.application.model_operation;

import cm.louisstark.gestock.entities.ModelOperation;
import cm.louisstark.gestock.entities.TypeOperation;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
@ManagedBean(name = "modelOperationController")
@ViewScoped
public class ModelOperationController extends AbstractModelOperationController implements Serializable {

    /**
     * Creates a new instance of MenuController
     */
    public ModelOperationController() {
    }

    public void save() {
        try {

            if (mode.equals("Create")) {

                typeOperation = typeOperationFacadeLocal.find(typeOperation.getIdType());
                if (typeOperation == null) {
                    typeOperation = new TypeOperation();
                    throw new Exception("Vous devez choisir un type d'opération");
                }

                modelOperation.setTypeOperation(typeOperation);
                modelOperation.setIdModel(modelOperationFacadeLocal.nextId());

                modelOperationFacadeLocal.create(modelOperation);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Enregistrement d'une entrée dans les modèles d'opération. -- Modèle: " + modelOperation.getLibelle(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Enregistrement éffectué !");

                PrimeFaces.current().executeScript("PF('CreateDialog').hide()");

            } else {
                if (mode.equals("Edit") && modelOperation.getIdModel() != null && modelOperation.getIdModel() != 0) {

                    typeOperation = typeOperationFacadeLocal.find(typeOperation.getIdType());
                    if (typeOperation == null) {
                        typeOperation = new TypeOperation();
                        throw new Exception("Vous devez choisir un type d'opération");
                    }
                    
                    modelOperationFacadeLocal.edit(modelOperation);

                    Utilitaires.saveActivity(mouchardFacadeLocal, "Mise a jour d'une entrée dans les modèles d'opération. -- Modèle: " + modelOperation.getLibelle(), sessionManager.getSessionUser());
                    Utilitaires.addSuccessMessage("Mise a jour éffectué !");

                    PrimeFaces.current().executeScript("PF('CreateDialog').hide()");

                } else {
                    Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné de menu à modifier");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void delete() {
        try {
            if (modelOperation.getIdModel() != null && modelOperation.getIdModel() != 0) {

                modelOperationFacadeLocal.remove(modelOperation);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Suppression d'une entrée dans les modèles d'opération. -- Modèle: " + modelOperation.getLibelle(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Suppression éffectué !");

                modelOperation = new ModelOperation();
            } else {
                Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné de menu à supprimer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
