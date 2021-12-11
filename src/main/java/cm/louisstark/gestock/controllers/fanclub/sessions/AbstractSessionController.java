/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.fanclub.sessions;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.SessionOp;
import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Louis Stark
 */
public abstract class AbstractSessionController extends SuperController {
    protected String date_debut = "", date_fin = "";
    protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    protected List<SessionOp> list_filteredSession = new ArrayList<>();

    @Override
    public void define_create_update_delete_details(Object o) {
        try {
            if (o != null && o instanceof SessionOp) {
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
    public void define_list_sessions() {
        try {
            if (fanClub != null && fanClub.getIdFanclub() != null && fanClub.getIdFanclub() != 0) {
                list_sessions = sessionFacadeLocal.findAllBy_societe(fanClub);
            } else {
                list_sessions = sessionFacadeLocal.findAll();
            }
        } catch (Exception e) {
        }
    }


    public void prepareCreate() {
        mode = "Create";
        try {
            session = new SessionOp(0);
            fanClub = new FanClub(0);

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }

    }

    public void prepareEdit() {

        mode = "Edit";
        try {
            
            if (session.getFanClub() != null) {
                fanClub = session.getFanClub();
            } else {
                fanClub = new FanClub(0);
            }
            
            date_debut = format.format(session.getDateDebut());
            date_fin = format.format(session.getDateFin());

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public List<SessionOp> getList_filteredSession() {
        return list_filteredSession;
    }

    public void setList_filteredSession(List<SessionOp> list_filteredSession) {
        this.list_filteredSession = list_filteredSession;
    }

}
