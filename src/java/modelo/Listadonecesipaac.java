/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author Hugo Campos
 */
@Entity
@Table(name = "listadonecesipaac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listadonecesipaac.findAll", query = "SELECT l FROM Listadonecesipaac l"),
    @NamedQuery(name = "Listadonecesipaac.findByEjerciciofiscal", query = "SELECT l FROM Listadonecesipaac l WHERE l.ejerciciofiscal = :ejerciciofiscal"),
    @NamedQuery(name = "Listadonecesipaac.findByNombreinstitucion", query = "SELECT l FROM Listadonecesipaac l WHERE l.nombreinstitucion = :nombreinstitucion"),
    @NamedQuery(name = "Listadonecesipaac.findByNombresolicitante", query = "SELECT l FROM Listadonecesipaac l WHERE l.nombresolicitante = :nombresolicitante"),
    @NamedQuery(name = "Listadonecesipaac.findByFechaelaboracion", query = "SELECT l FROM Listadonecesipaac l WHERE l.fechaelaboracion = :fechaelaboracion")})
public class Listadonecesipaac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "EJERCICIOFISCAL")
    private String ejerciciofiscal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBREINSTITUCION")
    private String nombreinstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRESOLICITANTE")
    private String nombresolicitante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAELABORACION")
    @Temporal(TemporalType.DATE)
    private Date fechaelaboracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listadonecesipaac")
    private Collection<Detallenecesipaac> detallenecesipaacCollection;
    @JoinColumn(name = "CODIGOINSTITUCION", referencedColumnName = "CODIGOINSTITUCION")
    @ManyToOne(optional = false)
    private Datosinstitucionales codigoinstitucion;

    public Listadonecesipaac() {
    }

    public Listadonecesipaac(String ejerciciofiscal) {
        this.ejerciciofiscal = ejerciciofiscal;
    }

    public Listadonecesipaac(String ejerciciofiscal, String nombreinstitucion, String nombresolicitante, Date fechaelaboracion) {
        this.ejerciciofiscal = ejerciciofiscal;
        this.nombreinstitucion = nombreinstitucion;
        this.nombresolicitante = nombresolicitante;
        this.fechaelaboracion = fechaelaboracion;
    }

    public String getEjerciciofiscal() {
        return ejerciciofiscal;
    }

    public void setEjerciciofiscal(String ejerciciofiscal) {
        this.ejerciciofiscal = ejerciciofiscal;
    }

    public String getNombreinstitucion() {
        return nombreinstitucion;
    }

    public void setNombreinstitucion(String nombreinstitucion) {
        this.nombreinstitucion = nombreinstitucion;
    }

    public String getNombresolicitante() {
        return nombresolicitante;
    }

    public void setNombresolicitante(String nombresolicitante) {
        this.nombresolicitante = nombresolicitante;
    }

    public Date getFechaelaboracion() {
        return fechaelaboracion;
    }

    public void setFechaelaboracion(Date fechaelaboracion) {
        this.fechaelaboracion = fechaelaboracion;
    }

    @XmlTransient
    public Collection<Detallenecesipaac> getDetallenecesipaacCollection() {
        return detallenecesipaacCollection;
    }

    public void setDetallenecesipaacCollection(Collection<Detallenecesipaac> detallenecesipaacCollection) {
        this.detallenecesipaacCollection = detallenecesipaacCollection;
    }

    public Datosinstitucionales getCodigoinstitucion() {
        return codigoinstitucion;
    }

    public void setCodigoinstitucion(Datosinstitucionales codigoinstitucion) {
        this.codigoinstitucion = codigoinstitucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejerciciofiscal != null ? ejerciciofiscal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listadonecesipaac)) {
            return false;
        }
        Listadonecesipaac other = (Listadonecesipaac) object;
        if ((this.ejerciciofiscal == null && other.ejerciciofiscal != null) || (this.ejerciciofiscal != null && !this.ejerciciofiscal.equals(other.ejerciciofiscal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Listadonecesipaac[ ejerciciofiscal=" + ejerciciofiscal + " ]";
    }
    
}
