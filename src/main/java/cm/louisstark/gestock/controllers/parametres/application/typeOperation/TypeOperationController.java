/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.application.typeOperation;

import cm.louisstark.gestock.entities.TypeMembre;
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
@ManagedBean(name = "typeOperationController")
@ViewScoped
public class TypeOperationController extends AbstractTypeOperationController implements Serializable{

    /**
     * Creates a new instance of MenuController
     */
    public TypeOperationController() {
    }

    public void save() {
        try {

            if (mode.equals("Create")) {
                
                typeOperation.setIdType(typeOperationFacadeLocal.nextId());

                typeOperationFacadeLocal.create(typeOperation);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Enregistrement d'une entrée dans les Types d'opération. -- Type: " + typeOperation.getLibelle(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Enregistrement éffectué !");

                PrimeFaces.current().executeScript("PF('CreateDialog').hide()");
               

            } else {
                if (mode.equals("Edit") && typeOperation.getIdType() != null && typeOperation.getIdType() != 0) {
                    
                    typeOperationFacadeLocal.edit(typeOperation);

                    Utilitaires.saveActivity(mouchardFacadeLocal, "Mise a jour d'une entrée dans les Types d'opération. -- Type: " + typeOperation.getLibelle(), sessionManager.getSessionUser());
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
            if (typeOperation.getIdType() != null && typeOperation.getIdType() != 0) {
                
                typeOperationFacadeLocal.remove(typeOperation);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Suppression d'une entrée dans les Types d'opération. -- Type: " + typeMembre.getLibelle(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Suppression éffectué !");

                typeOperation = new TypeOperation();
            } else {
                Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné de menu à supprimer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
