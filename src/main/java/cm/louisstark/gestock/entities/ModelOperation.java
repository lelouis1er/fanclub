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
@Table(name = "ModelOperation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModelOperation.findAll", query = "SELECT m FROM ModelOperation m"),
    @NamedQuery(name = "ModelOperation.findByIdModel", query = "SELECT m FROM ModelOperation m WHERE m.idModel = :idModel"),
    @NamedQuery(name = "ModelOperation.findByLibelle", query = "SELECT m FROM ModelOperation m WHERE m.libelle = :libelle")})
public class ModelOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idModel;
    @Size(max = 254)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modelOperation", fetch = FetchType.LAZY)
    private List<Operation> operationList;
    @JoinColumns({
        @JoinColumn(name = "idType", referencedColumnName = "idType"),
        @JoinColumn(name = "idType", referencedColumnName = "idType")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeOperation typeOperation;

    public ModelOperation() {
    }

    public ModelOperation(Integer idModel) {
        this.idModel = idModel;
    }

    public Integer getIdModel() {
        return idModel;
    }

    public void setIdModel(Integer idModel) {
        this.idModel = idModel;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModel != null ? idModel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelOperation)) {
            return false;
        }
        ModelOperation other = (ModelOperation) object;
        if ((this.idModel == null && other.idModel != null) || (this.idModel != null && !this.idModel.equals(other.idModel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.ModelOperation[ idModel=" + idModel + " ]";
    }
    
}
