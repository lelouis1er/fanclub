/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.security.utilisateurs;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.entities.RestrictionPrivilege;
import cm.louisstark.gestock.entities.RolePrivilege;
import cm.louisstark.gestock.entities.RoleUtilisateur;
import cm.louisstark.gestock.entities.Utilisateur;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Louis Stark
 */
public abstract class AbstractUtilisateursController extends SuperController {

    protected List<PrivilegesUtilisateur> list_selectedPrivileges = new ArrayList<>();
    protected List<RestrictionPrivilege> list_restrictionprivileges_for_delete = new ArrayList<>();

    protected DualListModel<PrivilegesUtilisateur> dual_list_priv = new DualListModel<>();

    @Override
    public void define_create_update_delete_details(Object o) {
        try {
            if (o != null && o instanceof Utilisateur) {
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
    public void define_list_roleutilisateur() {
        try {
            list_roleutilisateurs = roleutilisateurFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }

    @Override
    public void define_list_utilisateurs() {
        try {
            list_utilisateurs = utilisateurFacadeLocal.findAll_(false);
        } catch (Exception e) {
        }
    }

    @Override
    public void define_list_membres() {
        try {
            list_membres = membreFacadeLocal.findAll();
        } catch (Exception e) {
        }
    }

    public void prepareCreate() {

        mode = "Create";
        try {
            utilisateur = new Utilisateur();
            roleutilisateur = new RoleUtilisateur(0);
            membre = new Membre(0);
            
            list_privilegesutilisateurs = privilegesutilisateurFacadeLocal.findAll();
            list_roleprivileges.clear();
            list_selectedPrivileges.clear();
            list_restrictionprivileges_for_delete.clear();

            PrimeFaces.current().executeScript("PF('CreateDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void prepareAddPrivilege() {
        try {
            list_selectedPrivileges.clear();
            int index;
            for (RolePrivilege r : list_roleprivileges) {
                index = position_of(r.getPrivilegesUtilisateur());
                list_selectedPrivileges.add(r.getPrivilegesUtilisateur());
                if (index != -1) {
                    list_privilegesutilisateurs.remove(index);
                }
            }
            for (RestrictionPrivilege r : list_restrictionprivileges_for_delete) {
                list_privilegesutilisateurs.add(r.getPrivilegesUtilisateur());
            }
            list_restrictionprivileges_for_delete.clear();
            
            dual_list_priv.setTarget(list_selectedPrivileges);
            dual_list_priv.setSource(list_privilegesutilisateurs);

            PrimeFaces.current().executeScript("PF('ChoixPrivilegesDialog').show()");
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public void prepareEdit() {

        mode = "Edit";
        try {
            if (utilisateur != null && utilisateur.getIdUtilisateur() != null && utilisateur.getIdUtilisateur()!= 0) {
                
                if (utilisateur.getRoleUtilisateur() != null) {
                    roleutilisateur = utilisateur.getRoleUtilisateur();
                } else {
                    roleutilisateur = new RoleUtilisateur(0);
                }
                
                if (utilisateur.getMembre() != null) {
                    membre = utilisateur.getMembre();
                } else {
                    membre = new Membre(0);
                }
                
                list_privilegesutilisateurs = privilegesutilisateurFacadeLocal.findAll();
                list_restrictionprivileges = restrictionprivilegeFacadeLocal.findAll_by_Utilisateur(utilisateur);
                list_restrictionprivileges_for_delete.clear();

                PrimeFaces.current().executeScript("PF('CreateDialog').show()");
            }
        } catch (Exception e) {
            Utilitaires.addErrorMessage(e, "Message : " + e.getMessage());
        }
    }

    public int has_selected(PrivilegesUtilisateur p) {
        for (int i = 0; i < list_selectedPrivileges.size(); i++) {
            if (list_selectedPrivileges.get(i).getIdprivilege().equals(p.getIdprivilege())) {
                return i;
            }
        }
        return -1;
    }
    
    public int position_of (PrivilegesUtilisateur p) {
        for (int i = 0; i < list_privilegesutilisateurs.size(); i++) {
            if (list_privilegesutilisateurs.get(i).getIdprivilege().equals(p.getIdprivilege())) {
                return i;
            }
        }
        return -1;
    }

    public RestrictionPrivilege verifie_exist(RestrictionPrivilege r) {
        try {
            return restrictionprivilegeFacadeLocal.findAll_by_utilisateur_privilegeutilisateur(r.getUtilisateur(), r.getPrivilegesUtilisateur());
        } catch (Exception e) {
        }
        return null;
    }

    public List<PrivilegesUtilisateur> getList_selectedPrivileges() {
        return list_selectedPrivileges;
    }

    public void setList_selectedPrivileges(List<PrivilegesUtilisateur> list_selectedPrivileges) {
        this.list_selectedPrivileges = list_selectedPrivileges;
    }

    public DualListModel<PrivilegesUtilisateur> getDual_list_priv() {
        return dual_list_priv;
    }

    public void setDual_list_priv(DualListModel<PrivilegesUtilisateur> dual_list_priv) {
        this.dual_list_priv = dual_list_priv;
    }

}
