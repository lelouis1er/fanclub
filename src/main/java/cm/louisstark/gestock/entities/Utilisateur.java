/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lstark
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByIdUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :idUtilisateur"),
    @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT u FROM Utilisateur u WHERE u.login = :login"),
    @NamedQuery(name = "Utilisateur.findByPassword", query = "SELECT u FROM Utilisateur u WHERE u.password = :password"),
    @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateur.findByDescription", query = "SELECT u FROM Utilisateur u WHERE u.description = :description"),
    @NamedQuery(name = "Utilisateur.findByActif", query = "SELECT u FROM Utilisateur u WHERE u.actif = :actif"),
    @NamedQuery(name = "Utilisateur.findByDeleted", query = "SELECT u FROM Utilisateur u WHERE u.deleted = :deleted"),
    @NamedQuery(name = "Utilisateur.findByPhoto", query = "SELECT u FROM Utilisateur u WHERE u.photo = :photo"),
    @NamedQuery(name = "Utilisateur.findByDateCreation", query = "SELECT u FROM Utilisateur u WHERE u.dateCreation = :dateCreation"),
    @NamedQuery(name = "Utilisateur.findByDateDelete", query = "SELECT u FROM Utilisateur u WHERE u.dateDelete = :dateDelete"),
    @NamedQuery(name = "Utilisateur.findByDateDesactivation", query = "SELECT u FROM Utilisateur u WHERE u.dateDesactivation = :dateDesactivation"),
    @NamedQuery(name = "Utilisateur.findByDateActivation", query = "SELECT u FROM Utilisateur u WHERE u.dateActivation = :dateActivation")})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idUtilisateur;
    @Size(max = 254)
    private String login;
    @Size(max = 254)
    private String password;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String description;
    private Boolean actif;
    private Boolean deleted;
    @Size(max = 254)
    private String photo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDelete;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDesactivation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateActivation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private List<Mouchard> mouchardList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private List<RestrictionPrivilege> restrictionPrivilegeList;
    @JoinColumns({
        @JoinColumn(name = "idmembre", referencedColumnName = "idmembre"),
        @JoinColumn(name = "idmembre", referencedColumnName = "idmembre")})
    @ManyToOne(fetch = FetchType.LAZY)
    private Membre membre;
    @JoinColumns({
        @JoinColumn(name = "idrole", referencedColumnName = "idrole"),
        @JoinColumn(name = "idrole", referencedColumnName = "idrole")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RoleUtilisateur roleUtilisateur;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }

    public Date getDateDesactivation() {
        return dateDesactivation;
    }

    public void setDateDesactivation(Date dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }

    public Date getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(Date dateActivation) {
        this.dateActivation = dateActivation;
    }

    @XmlTransient
    public List<Mouchard> getMouchardList() {
        return mouchardList;
    }

    public void setMouchardList(List<Mouchard> mouchardList) {
        this.mouchardList = mouchardList;
    }

    @XmlTransient
    public List<RestrictionPrivilege> getRestrictionPrivilegeList() {
        return restrictionPrivilegeList;
    }

    public void setRestrictionPrivilegeList(List<RestrictionPrivilege> restrictionPrivilegeList) {
        this.restrictionPrivilegeList = restrictionPrivilegeList;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
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
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.Utilisateur[ idUtilisateur=" + idUtilisateur + " ]";
    }
    
}
