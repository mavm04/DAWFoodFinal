/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Ticket.findByCodTransaccion", query = "SELECT t FROM Ticket t WHERE t.codTransaccion = :codTransaccion"),
    @NamedQuery(name = "Ticket.findByHoraOperacion", query = "SELECT t FROM Ticket t WHERE t.horaOperacion = :horaOperacion"),
    @NamedQuery(name = "Ticket.findByImporteTotal", query = "SELECT t FROM Ticket t WHERE t.importeTotal = :importeTotal"),
    @NamedQuery(name = "Ticket.findByFechaOperacion", query = "SELECT t FROM Ticket t WHERE t.fechaOperacion = :fechaOperacion")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTicket")
    private Integer idTicket;
    @Basic(optional = false)
    @Column(name = "codTransaccion")
    private String codTransaccion;
    @Column(name = "horaOperacion")
    @Temporal(TemporalType.DATE)
    private Date horaOperacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importeTotal")
    private BigDecimal importeTotal;
    @Column(name = "fechaOperacion")
    @Temporal(TemporalType.DATE)
    private Date fechaOperacion;
    @JoinColumn(name = "IdTpv", referencedColumnName = "IdTpv")
    @ManyToOne
    private Tpv idTpv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private Collection<Detalleventa> detalleventaCollection;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, String codTransaccion) {
        this.idTicket = idTicket;
        this.codTransaccion = codTransaccion;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public String getCodTransaccion() {
        return codTransaccion;
    }

    public void setCodTransaccion(String codTransaccion) {
        this.codTransaccion = codTransaccion;
    }

    public Date getHoraOperacion() {
        return horaOperacion;
    }

    public void setHoraOperacion(Date horaOperacion) {
        this.horaOperacion = horaOperacion;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Tpv getIdTpv() {
        return idTpv;
    }

    public void setIdTpv(Tpv idTpv) {
        this.idTpv = idTpv;
    }

    @XmlTransient
    public Collection<Detalleventa> getDetalleventaCollection() {
        return detalleventaCollection;
    }

    public void setDetalleventaCollection(Collection<Detalleventa> detalleventaCollection) {
        this.detalleventaCollection = detalleventaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.Ticket[ idTicket=" + idTicket + " ]";
    }
    
}
