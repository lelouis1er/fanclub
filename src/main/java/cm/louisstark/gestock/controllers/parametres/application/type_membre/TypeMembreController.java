/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.application.type_membre;

import cm.louisstark.gestock.entities.TypeMembre;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
@ManagedBean(name = "typeMembreController")
@ViewScoped
public class TypeMembreController extends AbstractTypeMembreController implements Serializable{

    /**
     * Creates a new instance of MenuController
     */
    public TypeMembreController() {
    }

    public void save() {
        try {

            if (mode.equals("Create")) {
                
                typeMembre.setIdTypeMembre(typeMembreFacadeLocal.nextId());

                typeMembreFacadeLocal.create(typeMembre);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Enregistrement d'une entrée dans les Types de Membre. -- Type: " + typeMembre.getLibelle(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Enregistrement éffectué !");

                PrimeFaces.current().executeScript("PF('CreateDialog').hide()");
               

            } else {
                if (mode.equals("Edit") && typeMembre.getIdTypeMembre() != null && typeMembre.getIdTypeMembre() != 0) {
                    
                    typeMembreFacadeLocal.edit(typeMembre);

                    Utilitaires.saveActivity(mouchardFacadeLocal, "Mise a jour d'une entrée dans les Types de Membre. -- Type: " + typeMembre.getLibelle(), sessionManager.getSessionUser());
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
            if (typeMembre.getIdTypeMembre() != null && typeMembre.getIdTypeMembre() != 0) {
                
                typeMembreFacadeLocal.remove(typeMembre);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Suppression d'une entrée dans les Types de Membre. -- Type: " + typeMembre.getLibelle(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Suppression éffectué !");

                typeMembre = new TypeMembre(0);
            } else {
                Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné de menu à supprimer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
