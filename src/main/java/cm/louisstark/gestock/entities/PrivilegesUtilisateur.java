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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "PrivilegesUtilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrivilegesUtilisateur.findAll", query = "SELECT p FROM PrivilegesUtilisateur p"),
    @NamedQuery(name = "PrivilegesUtilisateur.findByIdprivilege", query = "SELECT p FROM PrivilegesUtilisateur p WHERE p.idprivilege = :idprivilege"),
    @NamedQuery(name = "PrivilegesUtilisateur.findByNom", query = "SELECT p FROM PrivilegesUtilisateur p WHERE p.nom = :nom"),
    @NamedQuery(name = "PrivilegesUtilisateur.findByDescription", query = "SELECT p FROM PrivilegesUtilisateur p WHERE p.description = :description"),
    @NamedQuery(name = "PrivilegesUtilisateur.findByNiveau", query = "SELECT p FROM PrivilegesUtilisateur p WHERE p.niveau = :niveau")})
public class PrivilegesUtilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idprivilege;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String description;
    private Integer niveau;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privilegesUtilisateur", fetch = FetchType.LAZY)
    private List<RestrictionPrivilege> restrictionPrivilegeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privilegesUtilisateur", fetch = FetchType.LAZY)
    private List<RolePrivilege> rolePrivilegeList;
    @JoinColumns({
        @JoinColumn(name = "idmenu", referencedColumnName = "idmenu"),
        @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu menu;

    public PrivilegesUtilisateur() {
    }

    public PrivilegesUtilisateur(Integer idprivilege) {
        this.idprivilege = idprivilege;
    }

    public Integer getIdprivilege() {
        return idprivilege;
    }

    public void setIdprivilege(Integer idprivilege) {
        this.idprivilege = idprivilege;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    @XmlTransient
    public List<RestrictionPrivilege> getRestrictionPrivilegeList() {
        return restrictionPrivilegeList;
    }

    public void setRestrictionPrivilegeList(List<RestrictionPrivilege> restrictionPrivilegeList) {
        this.restrictionPrivilegeList = restrictionPrivilegeList;
    }

    @XmlTransient
    public List<RolePrivilege> getRolePrivilegeList() {
        return rolePrivilegeList;
    }

    public void setRolePrivilegeList(List<RolePrivilege> rolePrivilegeList) {
        this.rolePrivilegeList = rolePrivilegeList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprivilege != null ? idprivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrivilegesUtilisateur)) {
            return false;
        }
        PrivilegesUtilisateur other = (PrivilegesUtilisateur) object;
        if ((this.idprivilege == null && other.idprivilege != null) || (this.idprivilege != null && !this.idprivilege.equals(other.idprivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.PrivilegesUtilisateur[ idprivilege=" + idprivilege + " ]";
    }
    
}
