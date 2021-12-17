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
@Table(name = "SessionOp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SessionOp.findAll", query = "SELECT s FROM SessionOp s"),
    @NamedQuery(name = "SessionOp.findByIdSession", query = "SELECT s FROM SessionOp s WHERE s.idSession = :idSession"),
    @NamedQuery(name = "SessionOp.findByLibelle", query = "SELECT s FROM SessionOp s WHERE s.libelle = :libelle"),
    @NamedQuery(name = "SessionOp.findByDateDebut", query = "SELECT s FROM SessionOp s WHERE s.dateDebut = :dateDebut"),
    @NamedQuery(name = "SessionOp.findByDateFin", query = "SELECT s FROM SessionOp s WHERE s.dateFin = :dateFin")})
public class SessionOp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idSession;
    @Size(max = 254)
    private String libelle;
    private Date dateDebut;
    private Date dateFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionOp", fetch = FetchType.LAZY)
    private List<Operation> operationList;
    @JoinColumns({
        @JoinColumn(name = "idFanclub", referencedColumnName = "idFanclub"),
        @JoinColumn(name = "idFanclub", referencedColumnName = "idFanclub")})
    @ManyToOne(fetch = FetchType.LAZY)
    private FanClub fanClub;

    public SessionOp() {
    }

    public SessionOp(Integer idSession) {
        this.idSession = idSession;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @XmlTransient
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public FanClub getFanClub() {
        return fanClub;
    }

    public void setFanClub(FanClub fanClub) {
        this.fanClub = fanClub;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSession != null ? idSession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionOp)) {
            return false;
        }
        SessionOp other = (SessionOp) object;
        if ((this.idSession == null && other.idSession != null) || (this.idSession != null && !this.idSession.equals(other.idSession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.SessionOp[ idSession=" + idSession + " ]";
    }
    
}
