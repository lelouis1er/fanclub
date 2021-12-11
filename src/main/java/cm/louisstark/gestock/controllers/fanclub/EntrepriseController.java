/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.fanclub;

import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
@ManagedBean(name = "entrepriseController")
@ViewScoped
public class EntrepriseController extends AbstractFanClubController implements Serializable {

    /**
     * Creates a new instance of MenuController
     */
    public EntrepriseController() {
    }

    public void save() {
        try {

            if (mode.equals("Create")) {

                fanClub.setIdFanclub(fanClubFacadeLocal.nextId());
                //fanClub.setDateEnregistrement(new Date());
                fanClub.setDateCreation(format.parse(date_creation));

                fanClubFacadeLocal.create(fanClub);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Enregistrement d'un nouveau Fanclub. -- Fanclub: " + fanClub.getNom(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Enregistrement éffectué !");

                PrimeFaces.current().executeScript("PF('CreateDialog').hide()");
                

            } else {
                if (mode.equals("Edit")) {
                    if (fanClub.getIdFanclub() != null && fanClub.getIdFanclub() != 0) {
                        
                        fanClubFacadeLocal.edit(fanClub);

                        Utilitaires.saveActivity(mouchardFacadeLocal, "Mise a jour des informations du fanClub. -- Fanclub: " + fanClub.getNom(), sessionManager.getSessionUser());
                        Utilitaires.addSuccessMessage("Mise a jour éffectué !");

                        PrimeFaces.current().executeScript("PF('CreateDialog').hide()");

                    } else {
                        Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné de fanClub à modifier");
                    }
                } else {
                    Utilitaires.addErrorMessage("Erreur : ", "mode non pris en charge !");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void delete() {
        try {
            if (fanClub.getIdFanclub() != null && fanClub.getIdFanclub() != 0) {

                fanClubFacadeLocal.remove(fanClub);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Suppression du fanclub. -- Fanclub : " + fanClub.getNom(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Suppression éffectué !");

                fanClub = new FanClub(0);
            } else {
                Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné le fanclub à supprimer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
