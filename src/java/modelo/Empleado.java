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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdempleado", query = "SELECT e FROM Empleado e WHERE e.idempleado = :idempleado"),
    @NamedQuery(name = "Empleado.findByDireccionemp", query = "SELECT e FROM Empleado e WHERE e.direccionemp = :direccionemp"),
    @NamedQuery(name = "Empleado.findByNombreempleado", query = "SELECT e FROM Empleado e WHERE e.nombreempleado = :nombreempleado"),
    @NamedQuery(name = "Empleado.findByApellidoempleado", query = "SELECT e FROM Empleado e WHERE e.apellidoempleado = :apellidoempleado"),
    @NamedQuery(name = "Empleado.findByCargoemp", query = "SELECT e FROM Empleado e WHERE e.cargoemp = :cargoemp"),
    @NamedQuery(name = "Empleado.findByTelefonoemp", query = "SELECT e FROM Empleado e WHERE e.telefonoemp = :telefonoemp"),
    @NamedQuery(name = "Empleado.findByCorreoelectronicoemp", query = "SELECT e FROM Empleado e WHERE e.correoelectronicoemp = :correoelectronicoemp"),
    @NamedQuery(name = "Empleado.findByObservacionesemp", query = "SELECT e FROM Empleado e WHERE e.observacionesemp = :observacionesemp")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEMPLEADO")
    private Integer idempleado;
    @Size(max = 30)
    @Column(name = "DIRECCIONEMP")
    private String direccionemp;
    @Size(max = 30)
    @Column(name = "NOMBREEMPLEADO")
    private String nombreempleado;
    @Size(max = 30)
    @Column(name = "APELLIDOEMPLEADO")
    private String apellidoempleado;
    @Size(max = 30)
    @Column(name = "CARGOEMP")
    private String cargoemp;
    @Size(max = 30)
    @Column(name = "TELEFONOEMP")
    private String telefonoemp;
    @Size(max = 30)
    @Column(name = "CORREOELECTRONICOEMP")
    private String correoelectronicoemp;
    @Size(max = 30)
    @Column(name = "OBSERVACIONESEMP")
    private String observacionesemp;
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER")
    @ManyToOne
    private Usuario iduser;

    public Empleado() {
    }

    public Empleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public String getDireccionemp() {
        return direccionemp;
    }

    public void setDireccionemp(String direccionemp) {
        this.direccionemp = direccionemp;
    }

    public String getNombreempleado() {
        return nombreempleado;
    }

    public void setNombreempleado(String nombreempleado) {
        this.nombreempleado = nombreempleado;
    }

    public String getApellidoempleado() {
        return apellidoempleado;
    }

    public void setApellidoempleado(String apellidoempleado) {
        this.apellidoempleado = apellidoempleado;
    }

    public String getCargoemp() {
        return cargoemp;
    }

    public void setCargoemp(String cargoemp) {
        this.cargoemp = cargoemp;
    }

    public String getTelefonoemp() {
        return telefonoemp;
    }

    public void setTelefonoemp(String telefonoemp) {
        this.telefonoemp = telefonoemp;
    }

    public String getCorreoelectronicoemp() {
        return correoelectronicoemp;
    }

    public void setCorreoelectronicoemp(String correoelectronicoemp) {
        this.correoelectronicoemp = correoelectronicoemp;
    }

    public String getObservacionesemp() {
        return observacionesemp;
    }

    public void setObservacionesemp(String observacionesemp) {
        this.observacionesemp = observacionesemp;
    }

    public Usuario getIduser() {
        return iduser;
    }

    public void setIduser(Usuario iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempleado != null ? idempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idempleado == null && other.idempleado != null) || (this.idempleado != null && !this.idempleado.equals(other.idempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Empleado[ idempleado=" + idempleado + " ]";
    }
    
}
