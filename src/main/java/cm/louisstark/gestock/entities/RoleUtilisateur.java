/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lstark
 */
@Entity
@Table (name = "RoleUtilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleUtilisateur.findAll", query = "SELECT r FROM RoleUtilisateur r"),
    @NamedQuery(name = "RoleUtilisateur.findByIdrole", query = "SELECT r FROM RoleUtilisateur r WHERE r.idrole = :idrole"),
    @NamedQuery(name = "RoleUtilisateur.findByNomrole", query = "SELECT r FROM RoleUtilisateur r WHERE r.nomrole = :nomrole"),
    @NamedQuery(name = "RoleUtilisateur.findByCoderole", query = "SELECT r FROM RoleUtilisateur r WHERE r.coderole = :coderole")})
public class RoleUtilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrole;
    @Size(max = 254)
    private String nomrole;
    @Size(max = 254)
    private String coderole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleUtilisateur", fetch = FetchType.LAZY)
    private List<RolePrivilege> rolePrivilegeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleUtilisateur", fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurList;

    public RoleUtilisateur() {
    }

    public RoleUtilisateur(Integer idrole) {
        this.idrole = idrole;
    }

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getNomrole() {
        return nomrole;
    }

    public void setNomrole(String nomrole) {
        this.nomrole = nomrole;
    }

    public String getCoderole() {
        return coderole;
    }

    public void setCoderole(String coderole) {
        this.coderole = coderole;
    }

    @XmlTransient
    public List<RolePrivilege> getRolePrivilegeList() {
        return rolePrivilegeList;
    }

    public void setRolePrivilegeList(List<RolePrivilege> rolePrivilegeList) {
        this.rolePrivilegeList = rolePrivilegeList;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrole != null ? idrole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleUtilisateur)) {
            return false;
        }
        RoleUtilisateur other = (RoleUtilisateur) object;
        if ((this.idrole == null && other.idrole != null) || (this.idrole != null && !this.idrole.equals(other.idrole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.RoleUtilisateur[ idrole=" + idrole + " ]";
    }
    
}
