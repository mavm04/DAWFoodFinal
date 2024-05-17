/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "tpv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpv.findAll", query = "SELECT t FROM Tpv t"),
    @NamedQuery(name = "Tpv.findByIdTpv", query = "SELECT t FROM Tpv t WHERE t.idTpv = :idTpv"),
    @NamedQuery(name = "Tpv.findByFechaSistema", query = "SELECT t FROM Tpv t WHERE t.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "Tpv.findByDireccion", query = "SELECT t FROM Tpv t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Tpv.findByContrase\u00f1a", query = "SELECT t FROM Tpv t WHERE t.contrase\u00f1a = :contrase\u00f1a"),
    @NamedQuery(name = "Tpv.findByHoraSistema", query = "SELECT t FROM Tpv t WHERE t.horaSistema = :horaSistema")})
public class Tpv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTpv")
    private Integer idTpv;
    @Column(name = "fechaSistema")
    @Temporal(TemporalType.DATE)
    private Date fechaSistema;
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @Column(name = "horaSistema")
    @Temporal(TemporalType.TIME)
    private Date horaSistema;
    @OneToMany(mappedBy = "idTpv")
    private Collection<Ticket> ticketCollection;

    public Tpv() {
    }

    public Tpv(Integer idTpv) {
        this.idTpv = idTpv;
    }

    public Tpv(Integer idTpv, String contraseña) {
        this.idTpv = idTpv;
        this.contraseña = contraseña;
    }

    public Integer getIdTpv() {
        return idTpv;
    }

    public void setIdTpv(Integer idTpv) {
        this.idTpv = idTpv;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getHoraSistema() {
        return horaSistema;
    }

    public void setHoraSistema(Date horaSistema) {
        this.horaSistema = horaSistema;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTpv != null ? idTpv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpv)) {
            return false;
        }
        Tpv other = (Tpv) object;
        if ((this.idTpv == null && other.idTpv != null) || (this.idTpv != null && !this.idTpv.equals(other.idTpv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.Tpv[ idTpv=" + idTpv + " ]";
    }
    
}
