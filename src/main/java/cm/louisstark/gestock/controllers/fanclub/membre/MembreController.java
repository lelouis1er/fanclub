/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.fanclub.membre;

import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
@ManagedBean(name = "employesController")
@ViewScoped
public class MembreController extends AbstractMembreController implements Serializable {

    /**
     * Creates a new instance of MenuController
     */
    public MembreController() {
    }

    public void save() {
        System.out.println("==================================");
        try {

            if (mode.equals("Create")) {
                
                
                fanClub = fanClubFacadeLocal.find(fanClub.getIdFanclub());
                if (fanClub == null) {
                    fanClub = new FanClub(0);
                    throw new Exception("Entreprise non valide");
                }
                membre.setFanClub(fanClub);
                //membre.setDateEnregistrement(new Date());
                membre.setIdmembre(membreFacadeLocal.nextId());
                
                membreFacadeLocal.create(membre);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Enregistrement d'un nouveau membre. -- Membre: " + membre.getNom() + " " + membre.getPrenom(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Enregistrement éffectué !");

                PrimeFaces.current().executeScript("PF('CreateDialog').hide()");
                

            } else {
                if (mode.equals("Edit")) {
                    if (membre.getIdmembre() != null && membre.getIdmembre() != 0) {
                        
                        fanClub = fanClubFacadeLocal.find(fanClub.getIdFanclub());
                        if (fanClub == null) {
                            fanClub = new FanClub(0);
                            throw new Exception("Entreprise non valide");
                        }
                        membre.setFanClub(fanClub);
                        membreFacadeLocal.edit(membre);

                        Utilitaires.saveActivity(mouchardFacadeLocal, "Mise a jour des informations du membre. -- Membre: " + membre.getNom(), sessionManager.getSessionUser());
                        Utilitaires.addSuccessMessage("Mise a jour éffectué !");

                        PrimeFaces.current().executeScript("PF('CreateDialog').hide()");
                        
                    } else {
                        Utilitaires.addErrorMessage("Erreur : ", "Vous n'avez pas sélectionné de menu à modifier");
                    }
                } else {
                    Utilitaires.addErrorMessage("Erreur : ", "Mode non pris en charge");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void delete() {
        try {
            if (membre.getIdmembre()!= null && membre.getIdmembre()!= 0) {

                membreFacadeLocal.remove(membre);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Suppression du membre. -- Membre : " + membre.getNom(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Suppression éffectué !");

                membre = new Membre(0);
            } else {
                Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné le membre à supprimer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
