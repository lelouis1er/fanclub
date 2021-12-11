/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.security.roles;

import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.entities.RolePrivilege;
import cm.louisstark.gestock.entities.RoleUtilisateur;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Louis Stark
 */
@ManagedBean(name = "rolesutilisateurController")
@ViewScoped
public class RolesutilisateurController extends AbstractRolesutilisateurController implements Serializable {

    /**
     * Creates a new instance of MenuController
     */
    public RolesutilisateurController() {

    }

    public void save() {
        try {

            if (mode.equals("Create")) {

                if ("su".equals(roleutilisateur.getCoderole())) {
                    throw new Exception("Il ne peux exister qu'un seul role su (Superuser)");
                }
                ut.begin();

                roleutilisateur.setIdrole(roleutilisateurFacadeLocal.nextId());
                roleutilisateurFacadeLocal.create(roleutilisateur);

                save_add_privileges();

                ut.commit();

                Utilitaires.saveActivity(mouchardFacadeLocal, "Enregistrement d'une entrée dans les Rôles utilisateur. -- Rôle : " + roleutilisateur.getNomrole(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Enregistrement éffectué !");

                PrimeFaces.current().executeScript("PF('CreateDialog').hide()");

            } else {
                if (mode.equals("Edit")) {
                    if (roleutilisateur != null && roleutilisateur.getIdrole() != null && roleutilisateur.getIdrole() != 0) {
                        if ("su".equals(roleutilisateur.getCoderole())) {
                            throw new Exception("Il ne peux exister qu'un seul role su (Superuser)");
                        }
                        roleutilisateurFacadeLocal.edit(roleutilisateur);

                        save_edit_privileges();

                        Utilitaires.saveActivity(mouchardFacadeLocal, "Mise a jour d'une entrée dans les Rôles utilisateur. -- Rôle: " + roleutilisateur.getNomrole(), sessionManager.getSessionUser());
                        Utilitaires.addSuccessMessage("Mise a jour éffectué !");

                        PrimeFaces.current().executeScript("PF('CreateDialog').hide()");
                    } else {
                        Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné le rôle à modifier");
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

            list_roleprivileges_for_delete.clear();
            list_selectedPrivileges = dual_list_priv.getTarget();
            int index;
            for (int i = 0; i < list_roleprivileges.size(); i++) {
                index = has_selected(list_roleprivileges.get(i).getPrivilegesUtilisateur());
                if (index == -1) { // n'existe pas dans la liste des privileges sélectionné on le met en attent de suppression
                    list_roleprivileges_for_delete.add(list_roleprivileges.get(i));
                    list_roleprivileges.remove(i);
                } else {
                    list_selectedPrivileges.remove(i);
                }
            }
            RolePrivilege rp;
            for (PrivilegesUtilisateur p : list_selectedPrivileges) {
                rp = new RolePrivilege();
                rp.setPrivilegesUtilisateur(p);
                rp.setCanCreate(false);
                rp.setCanDelete(false);
                rp.setCanUpdate(false);

                list_roleprivileges.add(rp);
            }
            
            PrimeFaces.current().executeScript("PF('ChoixPrivilegesDialog').hide()");

        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void save_add_privileges() throws Exception {
        try {
            if (roleutilisateur != null && roleutilisateur.getIdrole() != null && roleutilisateur.getIdrole() != 0) {

                for (RolePrivilege r : list_roleprivileges) {
                    r.setRoleUtilisateur(roleutilisateur);
                    r.setIdroleprivilege(roleprivilegeFacadeLocal.nextId());

                    roleprivilegeFacadeLocal.create(r);
                }

            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void save_edit_privileges() throws Exception {
        try {
            if (roleutilisateur != null && roleutilisateur.getIdrole() != null && roleutilisateur.getIdrole() != 0) {
                RolePrivilege temp;
                for (RolePrivilege r : list_roleprivileges) {
                    temp = verifie_exist(r);
                    if (temp != null) {
                        if (r.getIdroleprivilege() != null) {
                            roleprivilegeFacadeLocal.edit(r);
                        }
                    } else {
                        r.setRoleUtilisateur(roleutilisateur);
                        r.setIdroleprivilege(roleprivilegeFacadeLocal.nextId());
                        roleprivilegeFacadeLocal.create(r);
                    }
                }
                for (RolePrivilege r : list_roleprivileges_for_delete) {
                    if (r.getIdroleprivilege() != null) {
                        roleprivilegeFacadeLocal.remove(r);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete() {
        try {
            if (roleutilisateur.getIdrole() != null && roleutilisateur.getIdrole() != 0) {
                if ("su".equals(roleutilisateur.getCoderole())) {
                    throw new Exception("le role Superadmin (su) ne peux etre supprimé !");
                }
                roleutilisateurFacadeLocal.remove(roleutilisateur);

                Utilitaires.saveActivity(mouchardFacadeLocal, "Suppression d'une entrée dans les Rôles utilisateur. -- Rôle: " + roleutilisateur.getNomrole(), sessionManager.getSessionUser());
                Utilitaires.addSuccessMessage("Suppression éffectué !");

                roleutilisateur = new RoleUtilisateur(0);
            } else {
                Utilitaires.addErrorMessage("Erreur", "Vous n'avez pas sélectionné le rôle à supprimer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

}
