/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author migue
 */
@Entity
@Table(name = "detalleventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleventa.findAll", query = "SELECT d FROM Detalleventa d"),
    @NamedQuery(name = "Detalleventa.findByCantidadProducto", query = "SELECT d FROM Detalleventa d WHERE d.cantidadProducto = :cantidadProducto"),
    @NamedQuery(name = "Detalleventa.findByIdProducto", query = "SELECT d FROM Detalleventa d WHERE d.detalleventaPK.idProducto = :idProducto"),
    @NamedQuery(name = "Detalleventa.findByIdTicket", query = "SELECT d FROM Detalleventa d WHERE d.detalleventaPK.idTicket = :idTicket")})
public class Detalleventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleventaPK detalleventaPK;
    @Column(name = "cantidadProducto")
    private Integer cantidadProducto;
    @JoinColumn(name = "IdProducto", referencedColumnName = "IdProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productos productos;
    @JoinColumn(name = "IdTicket", referencedColumnName = "IdTicket", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ticket ticket;

    public Detalleventa() {
    }

    public Detalleventa(DetalleventaPK detalleventaPK, Integer cantidadProducto, Productos productos, Ticket ticket) {
        this.detalleventaPK = detalleventaPK;
        this.cantidadProducto = cantidadProducto;
        this.productos = productos;
        this.ticket = ticket;
    }

    public DetalleventaPK getDetalleventaPK() {
        return detalleventaPK;
    }

    public void setDetalleventaPK(DetalleventaPK detalleventaPK) {
        this.detalleventaPK = detalleventaPK;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleventaPK != null ? detalleventaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleventa)) {
            return false;
        }
        Detalleventa other = (Detalleventa) object;
        if ((this.detalleventaPK == null && other.detalleventaPK != null) || (this.detalleventaPK != null && !this.detalleventaPK.equals(other.detalleventaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Detalleventa[ detalleventaPK=" + detalleventaPK + " ]";
    }
}
