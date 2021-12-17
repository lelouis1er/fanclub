/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lstark
 */
@Entity
@Table(name = "Mouchard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mouchard.findAll", query = "SELECT m FROM Mouchard m"),
    @NamedQuery(name = "Mouchard.findByIdMouchard", query = "SELECT m FROM Mouchard m WHERE m.idMouchard = :idMouchard"),
    @NamedQuery(name = "Mouchard.findByDescription", query = "SELECT m FROM Mouchard m WHERE m.description = :description"),
    @NamedQuery(name = "Mouchard.findByDateOperation", query = "SELECT m FROM Mouchard m WHERE m.dateOperation = :dateOperation"),
    @NamedQuery(name = "Mouchard.findByHeureOperation", query = "SELECT m FROM Mouchard m WHERE m.heureOperation = :heureOperation")})
public class Mouchard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mouchard")
    private Integer idMouchard;
    @Size(max = 254)
    private String description;
    @Column(name = "date_operation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOperation;
    @Column(name = "heure_operation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureOperation;
    @JoinColumns({
        @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur"),
        @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    public Mouchard() {
    }

    public Mouchard(Integer idMouchard) {
        this.idMouchard = idMouchard;
    }

    public Integer getIdMouchard() {
        return idMouchard;
    }

    public void setIdMouchard(Integer idMouchard) {
        this.idMouchard = idMouchard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Date getHeureOperation() {
        return heureOperation;
    }

    public void setHeureOperation(Date heureOperation) {
        this.heureOperation = heureOperation;
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
        hash += (idMouchard != null ? idMouchard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mouchard)) {
            return false;
        }
        Mouchard other = (Mouchard) object;
        if ((this.idMouchard == null && other.idMouchard != null) || (this.idMouchard != null && !this.idMouchard.equals(other.idMouchard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.Mouchard[ idMouchard=" + idMouchard + " ]";
    }
    
}
