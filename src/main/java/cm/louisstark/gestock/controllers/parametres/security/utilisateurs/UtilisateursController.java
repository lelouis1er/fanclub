/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.security.utilisateurs;

import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.entities.RestrictionPrivilege;
import cm.louisstark.gestock.entities.RoleUtilisateur;
import cm.louisstark.gestock.entities.Utilisateur;
import cm.louisstark.gestock.security.Security;
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
@ManagedBean(name = "utilisateursController")
@ViewScoped
public class UtilisateursController extends AbstractUtilisateursController implements Serializable {

    /**
     * Creates a new instance of MenuController
     */
    public UtilisateursController() {

    }

    public void save() {
        try {

            if (mode.equals("Create")) {

                if (!sessionManager.user_can_create()) {
                    throw new Exception("Action non autorisé");
                }

                membre = membreFacadeLocal.find(membre.getIdmembre());
                if (membre != null) {
                    utilisateur.setMembre(membre);
                } else {
                    membre = new Membre(0);
                }

                roleutilisateur = roleutilisateurFacadeLocal.find(roleutilisateur.getIdrole());
                if (roleutilisateur != null) {
                    ut.begin();

                    utilisateur.setRoleUtilisateur(roleutilisateur);
                    utilisateur.setPassword(Security.crypte(utilisateur.getPassword()));
                    utilisateur.setDateCreation(new Date());
                    utilisateur.setActif(true);
                    utilisateur.setDeleted(false);
                    utilisateur.setIdUtilisateur(utilisateurFacadeLocal.nextId());
                    utilisateurFacadeLocal.create(utilisateur);

                    save_add_privileges();

                    ut.commit();

                    Utilitaires.saveActivity(mouchardFacadeLocal, "Enregistrement d'une entrée dans les utilisateur. -- Utilisateur : " + utilisateur.getNom(), sessionManager.getSessionUser());
                    Utilitaires.addSuccessMessage("Enregistrement éffectué !");

                    PrimeFaces.current().executeScript("PF('CreateDialog').hide()");
                } else {
                    Utilitaires.addErrorMessage("Erreur : ", "Vous devez définir un rôle utilisateur");
                }

            } else {
                if (mode.equals("Edit")) {

                    if (!sessionManager.user_can_update()) {
                        throw new Exception("Action non autorisé");
                    }

                    if (utilisateur != null && utilisateur.getIdUtilisateur() != null && utilisateur.getIdUtilisateur() != 0) {
                        membre = membreFacadeLocal.find(membre.getIdmembre());
                        if (membre != null) {
                            utilisateur.setMembre(membre);
                        } else {
                            membre = new Membre(0);
                        }
                        roleutilisateur = roleutilisateurFacadeLocal.find(roleutilisateur.getIdrole());
                        if (roleutilisateur != null) {

                            utilisateur.setRoleUtilisateur(roleutilisateur);
                            utilisateurFacadeLocal.edit(utilisateur);

                            save_edit_privileges();

                            Utilitaires.saveActivity(mouchardFacadeLocal, "Mise a jour d'une entrée dans les utilisateurs. -- Utilisateur: " + utilisateur.getNom(), sessionManager.getSessionUser());
                            Utilitaires.addSuccessMessage("Mise a jour éffectué !");

                            PrimeFaces.current().executeScript("PF('CreateDialog').hide()");
                        } else {
                            roleutilisateur = new RoleUtilisateur(0);
                            Utilitaires.addErrorMessage("Erreur", "Vous devez définir un rôle utilisateur");
                        }
                    } else {
                        Utilitaires.addErrorMessage("Erreur : ", "Vous devez sélectionner utilisateur");
                    }

                } else {
                    Utilitaires.addErrorMessage("Erreur", "Mode non supporté !");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void addPrivileges() {
        try {

            list_restrictionprivileges_for_delete.clear();
            list_selectedPrivileges = dual_list_priv.getTarget();
            int index;
            for (int i = 0; i < list_restrictionprivileges.size(); i++) {
                index = has_selected(list_restrictionprivileges.get(i).getPrivilegesUtilisateur());
                if (index == -1) { // n'existe pas dans la liste des privileges sélectionné on le met en attent de suppression
                    list_restrictionprivileges_for_delete.add(list_restrictionprivileges.get(i));
                    list_restrictionprivileges.remove(i);
                } else {
                    list_selectedPrivileges.remove(i);
                }
            }
            RestrictionPrivilege rp;
            for (PrivilegesUtilisateur p : list_selectedPrivileges) {
                rp = new RestrictionPrivilege();
                rp.setPrivilegesUtilisateur(p);
                rp.setRestrictUp(false);
                rp.setCanCreate(false);
                rp.setCanDelete(false);
                rp.setCanUpdate(false);

                list_restrictionprivileges.add(rp);
            }

            PrimeFaces.current().executeScript("PF('ChoixPrivilegesDialog').hide()");

        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void save_add_privileges() throws Exception {
        try {
            if (utilisateur != null && utilisateur.getIdUtilisateur() != null && utilisateur.getIdUtilisateur() != 0) {

                for (RestrictionPrivilege r : list_restrictionprivileges) {
                    r.setUtilisateur(utilisateur);
                    r.setIdrestriction(restrictionprivilegeFacadeLocal.nextId());

                    restrictionprivilegeFacadeLocal.create(r);
                }

            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void save_edit_privileges() throws Exception {
        try {
            if (utilisateur != null && utilisateur.getIdUtilisateur() != null && utilisateur.getIdUtilisateur() != 0) {
                RestrictionPrivilege temp;
                for (RestrictionPrivilege r : list_restrictionprivileges) {
                    temp = verifie_exist(r);
                    if (temp != null) {
                        if (r.getIdrestriction() != null) {
                            restrictionprivilegeFacadeLocal.edit(r);
                        }
                    } else {
                        r.setUtilisateur(utilisateur);
                        r.setIdrestriction(restrictionprivilegeFacadeLocal.nextId());
                        restrictionprivilegeFacadeLocal.create(r);
                    }
                }
                for (RestrictionPrivilege r : list_restrictionprivileges_for_delete) {
                    if (r.getIdrestriction() != null) {
                        restrictionprivilegeFacadeLocal.remove(r);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete() {
        try {

            if (!sessionManager.user_can_delete()) {
                throw new Exception("Action non autorisé");
            }

            if (utilisateur.getIdUtilisateur() != null && utilisateur.getIdUtilisateur() != 0) {

                utilisateur.setDeleted(true);
                utilisateurFacadeLocal.edit(utilisateur);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Suppression d'une entrée dans les utilisateur. -- Utilisateur: " + utilisateur.getNom(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Suppression éffectué !");

                utilisateur = new Utilisateur(0);
            } else {
                Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné l'utilisateur à supprimer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
