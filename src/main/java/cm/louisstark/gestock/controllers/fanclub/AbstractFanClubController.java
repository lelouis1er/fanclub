/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.fanclub;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.text.SimpleDateFormat;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
public abstract class AbstractFanClubController extends SuperController {
    
    protected String date_creation = "";
    protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void define_create_update_delete_details(Object o) {
        try {
            if (o != null && o instanceof FanClub) {
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
    public void define_list_fanClubs() {
        try {
            list_fanClub = fanClubFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }

    

    public void prepareCreate() {
        mode = "Create";
        try {
            fanClub = new FanClub(0);

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void prepareEdit() {

        mode = "Edit";
        try {
            
            date_creation = format.format(fanClub.getDateCreation());
            
            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

}
