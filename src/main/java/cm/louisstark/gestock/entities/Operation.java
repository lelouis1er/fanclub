/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lstark
 */
@Entity
@Table(name = "Operation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o"),
    @NamedQuery(name = "Operation.findByIdOperation", query = "SELECT o FROM Operation o WHERE o.idOperation = :idOperation"),
    @NamedQuery(name = "Operation.findByDateOperation", query = "SELECT o FROM Operation o WHERE o.dateOperation = :dateOperation"),
    @NamedQuery(name = "Operation.findByMontant", query = "SELECT o FROM Operation o WHERE o.montant = :montant")})
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idOperation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOperation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float montant;
    @JoinColumns({
        @JoinColumn(name = "idmembre", referencedColumnName = "idmembre"),
        @JoinColumn(name = "idmembre", referencedColumnName = "idmembre")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Membre membre;
    @JoinColumns({
        @JoinColumn(name = "idModel", referencedColumnName = "idModel"),
        @JoinColumn(name = "idModel", referencedColumnName = "idModel")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ModelOperation modelOperation;
    @JoinColumns({
        @JoinColumn(name = "idSession", referencedColumnName = "idSession"),
        @JoinColumn(name = "idSession", referencedColumnName = "idSession")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SessionOp sessionOp;

    public Operation() {
    }

    public Operation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public ModelOperation getModelOperation() {
        return modelOperation;
    }

    public void setModelOperation(ModelOperation modelOperation) {
        this.modelOperation = modelOperation;
    }

    public SessionOp getSessionOp() {
        return sessionOp;
    }

    public void setSessionOp(SessionOp sessionOp) {
        this.sessionOp = sessionOp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperation != null ? idOperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.idOperation == null && other.idOperation != null) || (this.idOperation != null && !this.idOperation.equals(other.idOperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.Operation[ idOperation=" + idOperation + " ]";
    }
    
}
