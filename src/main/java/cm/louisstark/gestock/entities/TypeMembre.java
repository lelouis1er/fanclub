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
    @NamedQuery(name = "TypeMembre.findAll", query = "SELECT t FROM TypeMembre t"),
    @NamedQuery(name = "TypeMembre.findByIdTypeMembre", query = "SELECT t FROM TypeMembre t WHERE t.idTypeMembre = :idTypeMembre"),
    @NamedQuery(name = "TypeMembre.findByLibelle", query = "SELECT t FROM TypeMembre t WHERE t.libelle = :libelle")})
public class TypeMembre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idTypeMembre;
    @Size(max = 254)
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeMembre", fetch = FetchType.LAZY)
    private List<Membre> membreList;

    public TypeMembre() {
    }

    public TypeMembre(Integer idTypeMembre) {
        this.idTypeMembre = idTypeMembre;
    }

    public Integer getIdTypeMembre() {
        return idTypeMembre;
    }

    public void setIdTypeMembre(Integer idTypeMembre) {
        this.idTypeMembre = idTypeMembre;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<Membre> getMembreList() {
        return membreList;
    }

    public void setMembreList(List<Membre> membreList) {
        this.membreList = membreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeMembre != null ? idTypeMembre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeMembre)) {
            return false;
        }
        TypeMembre other = (TypeMembre) object;
        if ((this.idTypeMembre == null && other.idTypeMembre != null) || (this.idTypeMembre != null && !this.idTypeMembre.equals(other.idTypeMembre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.TypeMembre[ idTypeMembre=" + idTypeMembre + " ]";
    }
    
}
