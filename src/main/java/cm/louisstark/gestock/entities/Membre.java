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
@Table(name = "Membre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membre.findAll", query = "SELECT m FROM Membre m"),
    @NamedQuery(name = "Membre.findByIdmembre", query = "SELECT m FROM Membre m WHERE m.idmembre = :idmembre"),
    @NamedQuery(name = "Membre.findByMatricule", query = "SELECT m FROM Membre m WHERE m.matricule = :matricule"),
    @NamedQuery(name = "Membre.findByNom", query = "SELECT m FROM Membre m WHERE m.nom = :nom"),
    @NamedQuery(name = "Membre.findByPrenom", query = "SELECT m FROM Membre m WHERE m.prenom = :prenom"),
    @NamedQuery(name = "Membre.findByTel", query = "SELECT m FROM Membre m WHERE m.tel = :tel"),
    @NamedQuery(name = "Membre.findByDdn", query = "SELECT m FROM Membre m WHERE m.ddn = :ddn"),
    @NamedQuery(name = "Membre.findByLdn", query = "SELECT m FROM Membre m WHERE m.ldn = :ldn"),
    @NamedQuery(name = "Membre.findByEmail", query = "SELECT m FROM Membre m WHERE m.email = :email"),
    @NamedQuery(name = "Membre.findByCni", query = "SELECT m FROM Membre m WHERE m.cni = :cni"),
    @NamedQuery(name = "Membre.findByPssd", query = "SELECT m FROM Membre m WHERE m.pssd = :pssd")})
public class Membre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idmembre;
    @Size(max = 254)
    private String matricule;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String prenom;
    @Size(max = 254)
    private String tel;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ddn;
    @Size(max = 254)
    private String ldn;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    private String email;
    @Size(max = 254)
    private String cni;
    @Size(max = 254)
    private String pssd;
    @JoinColumns({
        @JoinColumn(name = "idFanclub", referencedColumnName = "idFanclub"),
        @JoinColumn(name = "idFanclub", referencedColumnName = "idFanclub")})
    @ManyToOne(fetch = FetchType.LAZY)
    private FanClub fanClub;
    @JoinColumns({
        @JoinColumn(name = "idTypeMembre", referencedColumnName = "idTypeMembre"),
        @JoinColumn(name = "idTypeMembre", referencedColumnName = "idTypeMembre")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeMembre typeMembre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre", fetch = FetchType.LAZY)
    private List<Operation> operationList;
    @OneToMany(mappedBy = "membre", fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurList;

    public Membre() {
    }

    public Membre(Integer idmembre) {
        this.idmembre = idmembre;
    }

    public Integer getIdmembre() {
        return idmembre;
    }

    public void setIdmembre(Integer idmembre) {
        this.idmembre = idmembre;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDdn() {
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public String getLdn() {
        return ldn;
    }

    public void setLdn(String ldn) {
        this.ldn = ldn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getPssd() {
        return pssd;
    }

    public void setPssd(String pssd) {
        this.pssd = pssd;
    }

    public FanClub getFanClub() {
        return fanClub;
    }

    public void setFanClub(FanClub fanClub) {
        this.fanClub = fanClub;
    }

    public TypeMembre getTypeMembre() {
        return typeMembre;
    }

    public void setTypeMembre(TypeMembre typeMembre) {
        this.typeMembre = typeMembre;
    }

    @XmlTransient
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmembre != null ? idmembre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.idmembre == null && other.idmembre != null) || (this.idmembre != null && !this.idmembre.equals(other.idmembre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cm.louisstark.gestock.entities.Membre[ idmembre=" + idmembre + " ]";
    }
    
}
