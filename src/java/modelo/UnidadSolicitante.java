/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Hugo Campos
 */
@Entity
@Table(name = "unidad_solicitante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadSolicitante.findAll", query = "SELECT u FROM UnidadSolicitante u"),
    @NamedQuery(name = "UnidadSolicitante.findByIdUs", query = "SELECT u FROM UnidadSolicitante u WHERE u.idUs = :idUs"),
    @NamedQuery(name = "UnidadSolicitante.findByNombreUnidad", query = "SELECT u FROM UnidadSolicitante u WHERE u.nombreUnidad = :nombreUnidad")})
public class UnidadSolicitante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_US")
    private Short idUs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOMBRE_UNIDAD")
    private String nombreUnidad;
    @OneToMany(mappedBy = "idUs")
    private Collection<Solicitudobra> solicitudobraCollection;

    public UnidadSolicitante() {
    }

    public UnidadSolicitante(Short idUs) {
        this.idUs = idUs;
    }

    public UnidadSolicitante(Short idUs, String nombreUnidad) {
        this.idUs = idUs;
        this.nombreUnidad = nombreUnidad;
    }

    public Short getIdUs() {
        return idUs;
    }

    public void setIdUs(Short idUs) {
        this.idUs = idUs;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    @XmlTransient
    public Collection<Solicitudobra> getSolicitudobraCollection() {
        return solicitudobraCollection;
    }

    public void setSolicitudobraCollection(Collection<Solicitudobra> solicitudobraCollection) {
        this.solicitudobraCollection = solicitudobraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUs != null ? idUs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadSolicitante)) {
            return false;
        }
        UnidadSolicitante other = (UnidadSolicitante) object;
        if ((this.idUs == null && other.idUs != null) || (this.idUs != null && !this.idUs.equals(other.idUs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.UnidadSolicitante[ idUs=" + idUs + " ]";
    }
    
}
