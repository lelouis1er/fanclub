/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lstark
 */
@Entity
@Table(name = "RolePrivilege")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolePrivilege.findAll", query = "SELECT r FROM RolePrivilege r"),
    @NamedQuery(name = "RolePrivilege.findByIdroleprivilege", query = "SELECT r FROM RolePrivilege r WHERE r.idroleprivilege = :idroleprivilege"),
    @NamedQuery(name = "RolePrivilege.findByCanCreate", query = "SELECT r FROM RolePrivilege r WHERE r.canCreate = :canCreate"),
    @NamedQuery(name = "RolePrivilege.findByCanUpdate", query = "SELECT r FROM RolePrivilege r WHERE r.canUpdate = :canUpdate"),
    @NamedQuery(name = "RolePrivilege.findByCanDelete", query = "SELECT r FROM RolePrivilege r WHERE r.canDelete = :canDelete")})
public class RolePrivilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idroleprivilege;
    private Boolean canCreate;
    private Boolean canUpdate;
    private Boolean canDelete;
    @JoinColumns({
        @JoinColumn(name = "idprivilege", referencedColumnName = "idprivilege"),
        @JoinColumn(name = "idprivilege", referencedColumnName = "idprivilege")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PrivilegesUtilisateur privilegesUtilisateur;
    @JoinColumns({
        @JoinColumn(name = "idrole", referencedColumnName = "idrole"),
        @JoinColumn(name = "idrole", referencedColumnName = "idrole")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RoleUtilisateur roleUtilisateur;

    public RolePrivilege() {
    }

    public RolePrivilege(Integer idroleprivilege) {
        this.idroleprivilege = idroleprivilege;
    }

    public Integer getIdroleprivilege() {
        return idroleprivilege;
    }

    public void setIdroleprivilege(Integer idroleprivilege) {
        this.idroleprivilege = idroleprivilege;
    }

    public Boolean getCanCreate() {
        return canCreate;
    }

    public void setCanCreate(Boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public PrivilegesUtilisateur getPrivilegesUtilisateur() {
        return privilegesUtilisateur;
    }

    public void setPrivilegesUtilisateur(PrivilegesUtilisateur privilegesUtilisateur) {
        this.privilegesUtilisateur = privilegesUtilisateur;
    }

    public RoleUtilisateur getRoleUtilisateur() {
        return roleUtilisateur;
    }

    public void setRoleUtilisateur(RoleUtilisateur roleUtilisateur) {
        this.roleUtilisateur = roleUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idroleprivilege != null ? idroleprivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePrivilege)) {
            return false;
        }
        RolePrivilege other = (RolePrivilege) object;
        if ((this.idroleprivilege == null && other.idroleprivilege != null) || (this.idroleprivilege != null && !this.idroleprivilege.equals(other.idroleprivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.RolePrivilege[ idroleprivilege=" + idroleprivilege + " ]";
    }
    
}
