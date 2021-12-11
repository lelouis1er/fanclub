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
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface SessionManager{

    FacesContext getContext();
    
    HttpServletRequest getRequest();
    
    HttpSession getSession();
    
    Utilisateur getSessionUser();
    
    Membre getMembreUser();
    
    void setSessionUser(Utilisateur user);
    
    String getSessionLoginUser();
    
    void logout();
    
    RoleUtilisateur getSessionRoleUser();
    
    List<RolePrivilege> getAllUserRolePrivileges();
    
    List<RestrictionPrivilege> getAllUserRestrictions();
    
    boolean user_have_access();
    
    boolean user_can_create();
    
    boolean user_can_update();
    
    boolean user_can_delete();
    
    Boolean is_employee ();
    
    FanClub get_user_enterprise();
    
    FanClub user_entreprise(Utilisateur u);
    
    String userEntrepriseName (Utilisateur u);
    
    SessionOp getCycleEntreprise();
    
    void setCycleEntreprise(SessionOp session);
    
    List<Mouchard> getUserLogedActivity();
    
    List<Mouchard> getUserActivity(Utilisateur u);
    
    List<Mouchard> getAllActivity() throws Exception;
    
    List<Menu> getAllmenu();
    
    boolean is_su ();
    
    int nbreSociete ();
    
    int nbreUtilisateurs();
    
}
