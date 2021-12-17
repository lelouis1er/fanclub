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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lstark
 */
@Entity
@Table(name = "FanClub")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FanClub.findAll", query = "SELECT f FROM FanClub f"),
    @NamedQuery(name = "FanClub.findByIdFanclub", query = "SELECT f FROM FanClub f WHERE f.idFanclub = :idFanclub"),
    @NamedQuery(name = "FanClub.findByNom", query = "SELECT f FROM FanClub f WHERE f.nom = :nom"),
    @NamedQuery(name = "FanClub.findByLogo", query = "SELECT f FROM FanClub f WHERE f.logo = :logo"),
    @NamedQuery(name = "FanClub.findByDestription", query = "SELECT f FROM FanClub f WHERE f.description = :description")})
public class FanClub implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idFanclub;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String logo;
    @Size(max = 254)
    private String description;
    private String telephone;
    private String email;
    private String siteweb;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    @OneToMany(mappedBy = "fanClub", fetch = FetchType.LAZY)
    private List<Membre> membreList;
    @OneToMany(mappedBy = "fanClub", fetch = FetchType.LAZY)
    private List<SessionOp> sessionOpList;

    public FanClub() {
    }

    public FanClub(Integer idFanclub) {
        this.idFanclub = idFanclub;
    }

    public Integer getIdFanclub() {
        return idFanclub;
    }

    public void setIdFanclub(Integer idFanclub) {
        this.idFanclub = idFanclub;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    @XmlTransient
    public List<Membre> getMembreList() {
        return membreList;
    }

    public void setMembreList(List<Membre> membreList) {
        this.membreList = membreList;
    }

    @XmlTransient
    public List<SessionOp> getSessionOpList() {
        return sessionOpList;
    }

    public void setSessionOpList(List<SessionOp> sessionOpList) {
        this.sessionOpList = sessionOpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFanclub != null ? idFanclub.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FanClub)) {
            return false;
        }
        FanClub other = (FanClub) object;
        if ((this.idFanclub == null && other.idFanclub != null) || (this.idFanclub != null && !this.idFanclub.equals(other.idFanclub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.FanClub[ idFanclub=" + idFanclub + " ]";
    }
    
}
