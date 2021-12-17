/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.fanclub.membre;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.TypeMembre;
import java.text.SimpleDateFormat;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
public abstract class AbstractMembreController extends SuperController {

    protected String ddn = "";
    protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    
    @Override
    public void define_create_update_delete_details(Object o) {
        try {
            if (o != null && o instanceof Membre) {
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


    @Override
    public void define_list_membres() {
        try {
            if (fanClub != null && fanClub.getIdFanclub() != null && fanClub.getIdFanclub() != 0) {
                list_membres = membreFacadeLocal.findAllBy_societe(fanClub);
            } else {
                list_membres = membreFacadeLocal.findAll();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void define_list_typeMembre() {
        try {
            list_typeMembres = typeMembreFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }
    

    public void prepareCreate() {
        mode = "Create";
        try {
            membre = new Membre();
            typeMembre = new TypeMembre(0);
            fanClub = new FanClub(0);

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }

    }

    public void prepareEdit() {

        mode = "Edit";
        try {

            if (membre.getFanClub() != null) {
                fanClub = membre.getFanClub();
            } else {
                fanClub = new FanClub(0);
            }
            
            if (membre.getTypeMembre() != null) {
                typeMembre = membre.getTypeMembre();
            } else {
                typeMembre = new TypeMembre(0);
            }
            ddn = format.format(membre.getDdn());

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public String getDdn() {
        return ddn;
    }

    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

}
