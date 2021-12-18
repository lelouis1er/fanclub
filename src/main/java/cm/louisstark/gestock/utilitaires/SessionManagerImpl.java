/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.utilitaires;

import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.Menu;
import cm.louisstark.gestock.entities.Mouchard;
import cm.louisstark.gestock.entities.RestrictionPrivilege;
import cm.louisstark.gestock.entities.RolePrivilege;
import cm.louisstark.gestock.entities.RoleUtilisateur;
import cm.louisstark.gestock.entities.SessionOp;
import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.entities.Utilisateur;
import cm.louisstark.gestock.sessions.MenuFacadeLocal;
import cm.louisstark.gestock.sessions.MouchardFacadeLocal;
import cm.louisstark.gestock.sessions.RestrictionprivilegeFacadeLocal;
import cm.louisstark.gestock.sessions.RoleprivilegeFacadeLocal;
import cm.louisstark.gestock.sessions.FanClubFacadeLocal;
import cm.louisstark.gestock.sessions.UtilisateurFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Louis Stark
 */
@ManagedBean(name = "manageBean")
@SessionScoped
public class SessionManagerImpl implements SessionManager, Serializable {

    @EJB
    protected RoleprivilegeFacadeLocal roleprivilegeFacadeLocal;

    @EJB
    protected RestrictionprivilegeFacadeLocal restrictionprivilegeFacadeLocal;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected MenuFacadeLocal menuFacadeLocal;

    @EJB
    protected FanClubFacadeLocal fanClubFacadeLocal;

    @EJB
    protected UtilisateurFacadeLocal utilisateurFacadeLocal;

    @Override
    public FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    @Override
    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getContext().getExternalContext().getRequest();
    }

    @Override
    public HttpSession getSession() {
        return (HttpSession) getRequest().getSession();
    }

    @Override
    public Utilisateur getSessionUser() {
        return (Utilisateur) getSession().getAttribute("user");
    }

    @Override
    public void setSessionUser(Utilisateur user) {
        (getSession()).setAttribute("user", user);
    }

    @Override
    public Membre getMembreUser() {
        Utilisateur user = getSessionUser();
        if (user.getMembre() != null) {
            return user.getMembre();
        }
        return null;
    }

    @Override
    public String getSessionLoginUser() {
        return getSessionUser().getLogin();
    }

    @Override
    public void logout() {
        Utilisateur usr = getSessionUser();
        getSession().setAttribute("user", null);
        getSession().setAttribute("cycle", null);

        Utilitaires.saveActivity(mouchardFacadeLocal, "Deconnexion de l'utilisateur (" + usr.getLogin() + ")", usr);
    }

    @Override
    public RoleUtilisateur getSessionRoleUser() {
        try {
            return getSessionUser().getRoleUtilisateur();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<RolePrivilege> getAllUserRolePrivileges() {
        return roleprivilegeFacadeLocal.findAll_by_RoleUtilisateur(getSessionRoleUser());
    }

    @Override
    public List<RestrictionPrivilege> getAllUserRestrictions() {
        return restrictionprivilegeFacadeLocal.findAll_by_Utilisateur(getSessionUser());
    }

    @Override
    public boolean user_have_access() {
        try {
            if (getSessionRoleUser().getCoderole().equals("su")) {
                return true;
            }
            try {
                HttpServletRequest request = getRequest();
                String uri = request.getRequestURI();
                //System.out.println("Uri : " + uri);
                for (RestrictionPrivilege r : getAllUserRestrictions()) {
                    if (uri.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                        return r.getRestrictUp();
                    }
                }
                for (RolePrivilege r : getAllUserRolePrivileges()) {
                    if (uri.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean user_can_create() {
        try {
            if (getSessionRoleUser().getCoderole().equals("su")) {
                return true;
            }
            HttpServletRequest request = getRequest();
            String uri = request.getRequestURI();
            for (RestrictionPrivilege r : getAllUserRestrictions()) {
                if (uri.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                    if (r.getRestrictUp()) {
                        return r.getCanCreate();
                    } else {
                        return false;
                    }
                }
            }
            for (RolePrivilege r : getAllUserRolePrivileges()) {
                if (uri.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                    if (r.getCanCreate()) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean user_can_update() {
        try {
            if (getSessionRoleUser().getCoderole().equals("su")) {
                return true;
            }
            HttpServletRequest request = getRequest();
            String uri = request.getRequestURI();
            for (RestrictionPrivilege r : getAllUserRestrictions()) {
                if (uri.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                    if (r.getRestrictUp()) {
                        return r.getCanUpdate();
                    } else {
                        return false;
                    }
                }
            }
            for (RolePrivilege r : getAllUserRolePrivileges()) {
                if (uri.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                    if (r.getCanUpdate()) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean user_can_delete() {
        try {
            if (getSessionRoleUser().getCoderole().equals("su")) {
                return true;
            }
            HttpServletRequest request = getRequest();
            String uri = request.getRequestURI();

            for (RestrictionPrivilege r : getAllUserRestrictions()) {
                if (uri.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                    if (r.getRestrictUp()) {
                        return r.getCanDelete();
                    } else {
                        return false;
                    }
                }
            }
            for (RolePrivilege r : getAllUserRolePrivileges()) {
                if (uri.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                    if (r.getCanDelete()) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean user_can_do_it(String action) {
        try {
            if (action == null || action == "") {
                return false;
            }
            if (getSessionRoleUser().getCoderole().equals("su")) {
                return true;
            }
            for (RestrictionPrivilege r : getAllUserRestrictions()) {
                if (action.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                    if (r.getRestrictUp()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            for (RolePrivilege r : getAllUserRolePrivileges()) {
                if (action.equals(r.getPrivilegesUtilisateur().getMenu().getRessource())) {
                    return true;
                }
            }

        } catch (Exception e) {
        }

        return false;
    }

    @Override
    public List<Mouchard> getUserLogedActivity() {
        return mouchardFacadeLocal.findAll_by_user(getSessionUser());
    }

    @Override
    public List<Mouchard> getUserActivity(Utilisateur u) {
        return mouchardFacadeLocal.findAll_by_user(u);
    }

    @Override
    public List<Mouchard> getAllActivity() throws Exception {
        if ("su".equals(getSessionRoleUser().getCoderole().trim())) {
            return mouchardFacadeLocal.findAllRange();
        }
        throw new Exception("Vous n'avez pas d'autorisation pour effectuer cette operation !");
    }

    @Override
    public List<Menu> getAllmenu() {
        return menuFacadeLocal.findAll();
    }

    @Override
    public Boolean is_employee() {
        if (getSessionUser() != null) {
            return getSessionUser().getMembre() != null;
        }
        return null;
    }

    @Override
    public FanClub get_user_enterprise() {
        try {
            if (!is_employee()) {
                return null;
            }
            return getSessionUser().getMembre().getFanClub();
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public SessionOp getCycleEntreprise() {
        if (!is_employee()) {
            return null;
        }
        return (SessionOp) getSession().getAttribute("cycle");
    }

    @Override
    public void setCycleEntreprise(SessionOp session) {
        if (!is_employee()) {
            return;
        }
        getSession().setAttribute("cycle", session);
    }

    @Override
    public boolean is_su() {
        return getSessionUser().getMembre() == null && "su".equals(getSessionRoleUser().getCoderole());
    }

    @Override
    public FanClub user_entreprise(Utilisateur u) {
        try {
            return u.getMembre().getFanClub();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public String userEntrepriseName(Utilisateur u) {
        try {
            return (user_entreprise(u)).getNom();
        } catch (Exception e) {
        }
        return "";
    }

    @Override
    public int nbreSociete() {
        int nbre = 0;
        try {
            nbre = fanClubFacadeLocal.findAll().size();
        } catch (Exception e) {
        }
        return nbre;
    }

    @Override
    public int nbreUtilisateurs() {
        int nbre = 0;
        try {
            nbre = utilisateurFacadeLocal.findAll().size();
        } catch (Exception e) {
        }
        return nbre;
    }

}
