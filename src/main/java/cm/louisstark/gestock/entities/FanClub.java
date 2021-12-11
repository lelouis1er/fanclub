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
    @NamedQuery(name = "FanClub.findAll", query = "SELECT f FROM FanClub f"),
    @NamedQuery(name = "FanClub.findByIdFanclub", query = "SELECT f FROM FanClub f WHERE f.idFanclub = :idFanclub"),
    @NamedQuery(name = "FanClub.findByNom", query = "SELECT f FROM FanClub f WHERE f.nom = :nom"),
    @NamedQuery(name = "FanClub.findByLogo", query = "SELECT f FROM FanClub f WHERE f.logo = :logo"),
    @NamedQuery(name = "FanClub.findByDestription", query = "SELECT f FROM FanClub f WHERE f.destription = :destription")})
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
    private String destription;
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

    public String getDestription() {
        return destription;
    }

    public void setDestription(String destription) {
        this.destription = destription;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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
