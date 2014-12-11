/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hugo Campos
 */
@Entity
@Table(name = "detallesolicitudobra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallesolicitudobra.findAll", query = "SELECT d FROM Detallesolicitudobra d"),
    @NamedQuery(name = "Detallesolicitudobra.findByIdDetalle", query = "SELECT d FROM Detallesolicitudobra d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "Detallesolicitudobra.findByCantidad", query = "SELECT d FROM Detallesolicitudobra d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallesolicitudobra.findByUnidadmedida", query = "SELECT d FROM Detallesolicitudobra d WHERE d.unidadmedida = :unidadmedida"),
    @NamedQuery(name = "Detallesolicitudobra.findByObrasolicitada", query = "SELECT d FROM Detallesolicitudobra d WHERE d.obrasolicitada = :obrasolicitada"),
    @NamedQuery(name = "Detallesolicitudobra.findByEspecifictecnicas", query = "SELECT d FROM Detallesolicitudobra d WHERE d.especifictecnicas = :especifictecnicas")})
public class Detallesolicitudobra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DETALLE")
    private Integer idDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "UNIDADMEDIDA")
    private String unidadmedida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "OBRASOLICITADA")
    private String obrasolicitada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ESPECIFICTECNICAS")
    private String especifictecnicas;
    @JoinColumn(name = "CODIGOUNSPSC", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Unspsc codigounspsc;
    @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
    @ManyToOne
    private Solicitudobra idSolicitud;

    public Detallesolicitudobra() {
    }

    public Detallesolicitudobra(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Detallesolicitudobra(Integer idDetalle, int cantidad, String unidadmedida, String obrasolicitada, String especifictecnicas) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.unidadmedida = unidadmedida;
        this.obrasolicitada = obrasolicitada;
        this.especifictecnicas = especifictecnicas;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public String getObrasolicitada() {
        return obrasolicitada;
    }

    public void setObrasolicitada(String obrasolicitada) {
        this.obrasolicitada = obrasolicitada;
    }

    public String getEspecifictecnicas() {
        return especifictecnicas;
    }

    public void setEspecifictecnicas(String especifictecnicas) {
        this.especifictecnicas = especifictecnicas;
    }

    public Unspsc getCodigounspsc() {
        return codigounspsc;
    }

    public void setCodigounspsc(Unspsc codigounspsc) {
        this.codigounspsc = codigounspsc;
    }

    public Solicitudobra getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitudobra idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallesolicitudobra)) {
            return false;
        }
        Detallesolicitudobra other = (Detallesolicitudobra) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Detallesolicitudobra[ idDetalle=" + idDetalle + " ]";
    }
    
}
