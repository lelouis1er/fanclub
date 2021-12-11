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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lstark
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestrictionPrivilege.findAll", query = "SELECT r FROM RestrictionPrivilege r"),
    @NamedQuery(name = "RestrictionPrivilege.findByIdrestriction", query = "SELECT r FROM RestrictionPrivilege r WHERE r.idrestriction = :idrestriction"),
    @NamedQuery(name = "RestrictionPrivilege.findByRestrictUp", query = "SELECT r FROM RestrictionPrivilege r WHERE r.restrictUp = :restrictUp"),
    @NamedQuery(name = "RestrictionPrivilege.findByCanCreate", query = "SELECT r FROM RestrictionPrivilege r WHERE r.canCreate = :canCreate"),
    @NamedQuery(name = "RestrictionPrivilege.findByCanUpdate", query = "SELECT r FROM RestrictionPrivilege r WHERE r.canUpdate = :canUpdate"),
    @NamedQuery(name = "RestrictionPrivilege.findByCanDelete", query = "SELECT r FROM RestrictionPrivilege r WHERE r.canDelete = :canDelete")})
public class RestrictionPrivilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrestriction;
    private Boolean restrictUp;
    private Boolean canCreate;
    private Boolean canUpdate;
    private Boolean canDelete;
    @JoinColumns({
        @JoinColumn(name = "idprivilege", referencedColumnName = "idprivilege"),
        @JoinColumn(name = "idprivilege", referencedColumnName = "idprivilege")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PrivilegesUtilisateur privilegesUtilisateur;
    @JoinColumns({
        @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur"),
        @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    public RestrictionPrivilege() {
    }

    public RestrictionPrivilege(Integer idrestriction) {
        this.idrestriction = idrestriction;
    }

    public Integer getIdrestriction() {
        return idrestriction;
    }

    public void setIdrestriction(Integer idrestriction) {
        this.idrestriction = idrestriction;
    }

    public Boolean getRestrictUp() {
        return restrictUp;
    }

    public void setRestrictUp(Boolean restrictUp) {
        this.restrictUp = restrictUp;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrestriction != null ? idrestriction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestrictionPrivilege)) {
            return false;
        }
        RestrictionPrivilege other = (RestrictionPrivilege) object;
        if ((this.idrestriction == null && other.idrestriction != null) || (this.idrestriction != null && !this.idrestriction.equals(other.idrestriction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.RestrictionPrivilege[ idrestriction=" + idrestriction + " ]";
    }
    
}
