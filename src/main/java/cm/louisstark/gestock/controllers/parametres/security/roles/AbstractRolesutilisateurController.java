/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.controllers.parametres.security.roles;

import cm.louisstark.gestock.controllers.SuperController;
import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.entities.RolePrivilege;
import cm.louisstark.gestock.entities.RoleUtilisateur;
import cm.louisstark.gestock.utilitaires.Utilitaires;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Louis Stark
 */
public abstract class AbstractRolesutilisateurController extends SuperController {

    protected List<PrivilegesUtilisateur> list_selectedPrivileges = new ArrayList<>();
    protected List<RolePrivilege> list_roleprivileges_for_delete = new ArrayList<>();

    protected DualListModel<PrivilegesUtilisateur> dual_list_priv = new DualListModel<>();

    @Override
    public void define_create_update_delete_details(Object o) {
        try {
            if (o != null && o instanceof RoleUtilisateur) {
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

    public void prepareCreate() {

        mode = "Create";
        try {
            roleutilisateur = new RoleUtilisateur(0);
            
            list_privilegesutilisateurs = privilegesutilisateurFacadeLocal.findAll();
            list_roleprivileges.clear();
            list_selectedPrivileges.clear();
            list_roleprivileges_for_delete.clear();

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
            for (RolePrivilege r : list_roleprivileges_for_delete) {
                list_privilegesutilisateurs.add(r.getPrivilegesUtilisateur());
            }
            list_roleprivileges_for_delete.clear();
            
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
            if (roleutilisateur != null && roleutilisateur.getIdrole() != null && roleutilisateur.getIdrole() != 0) {
                if ("su".equals(roleutilisateur.getCoderole())) {
                    throw new Exception("le role Superadmin (su) ne peux etre modifié");
                }
                list_privilegesutilisateurs = privilegesutilisateurFacadeLocal.findAll();
                list_roleprivileges = roleprivilegeFacadeLocal.findAll_by_RoleUtilisateur(roleutilisateur);
                list_roleprivileges_for_delete.clear();

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

    public RolePrivilege verifie_exist(RolePrivilege r) {
        try {
            return roleprivilegeFacadeLocal.findAll_by_roleutilisateur_privilegeutilisateur(r.getRoleUtilisateur(), r.getPrivilegesUtilisateur());
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
