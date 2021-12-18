/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.application;

import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.ModelOperation;
import cm.louisstark.gestock.entities.Operation;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author lstark
 */
@ManagedBean(name = "operationController")
@ViewScoped
public class OperationController extends AbstractOperationController implements Serializable {

    public void save() {
        try {

            if (mode.equals("Create")) {

                modelOperation = modelOperationFacadeLocal.find(modelOperation.getIdModel());
                if (modelOperation == null) {
                    modelOperation = new ModelOperation(0);
                    throw new Exception("Type d'opération non valide");
                }

                membre = membreFacadeLocal.find(membre.getIdmembre());
                if (membre == null) {
                    membre = new Membre(0);
                    throw new Exception("Membre non valide");
                }

                operation.setMembre(membre);
                operation.setModelOperation(modelOperation);

                operation.setDateOperation(format.parse(date_op));
                operation.setSessionOp(sessionManager.getCycleEntreprise());
                operation.setIdOperation(operationFacadeLocal.nextId());
                
                operationFacadeLocal.create(operation);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Enregistrement d'une nouvelle opération. -- Opération: " + operation.getModelOperation().getLibelle() + " -> " + operation.getMembre().getNom() + " (" + operation.getMontant() + ")", sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Enregistrement éffectué !");

                PrimeFaces.current().ajax().update(":ViewForm");
                PrimeFaces.current().executeScript("PF('CreateDialog').hide()");

            } else {
                if (mode.equals("Edit")) {
                    if (operation.getIdOperation() != null && operation.getIdOperation() != 0) {

                        modelOperation = modelOperationFacadeLocal.find(modelOperation.getIdModel());
                        if (modelOperation == null) {
                            modelOperation = new ModelOperation(0);
                            throw new Exception("Type d'opération non valide");
                        }

                        membre = membreFacadeLocal.find(membre.getIdmembre());
                        if (membre == null) {
                            membre = new Membre(0);
                            throw new Exception("Membre non valide");
                        }

                        operation.setMembre(membre);
                        operation.setModelOperation(modelOperation);
                        operation.setDateOperation(format.parse(date_op));

                        operationFacadeLocal.edit(operation);

                        Utilitaires.saveActivity(mouchardFacadeLocal, "Mise a jour des informations de l'opération. -- Opération: " + operation.getModelOperation().getLibelle() + " -> " + operation.getMembre().getNom() + " (" + operation.getMontant() + ")", sessionManager.getSessionUser());
                        Utilitaires.addSuccessMessage("Mise a jour éffectué !");

                        PrimeFaces.current().ajax().update(":ViewForm");
                        PrimeFaces.current().executeScript("PF('CreateDialog').hide()");

                    } else {
                        Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné de menu à modifier");
                    }
                } else {
                    Utilitaires.addErrorMessage("Erreur : ", "Mode non pris en charge !");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void delete() {
        try {
            if (operation.getIdOperation() != null && operation.getIdOperation() != 0) {

                operationFacadeLocal.remove(operation);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Suppression de l'oprération. -- opération : " + operation.getModelOperation().getLibelle() + " -> " + operation.getMembre().getNom() + " (" + operation.getMontant() + ")", sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Suppression éffectué !");

                operation = new Operation();
            } else {
                Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné l'élément à supprimer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
