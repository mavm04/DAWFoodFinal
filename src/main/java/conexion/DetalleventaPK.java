/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author miguel
 */
@Embeddable
public class DetalleventaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IdProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "IdTicket")
    private int idTicket;

    public DetalleventaPK() {
    }

    public DetalleventaPK(int idProducto, int idTicket) {
        this.idProducto = idProducto;
        this.idTicket = idTicket;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (int) idTicket;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleventaPK)) {
            return false;
        }
        DetalleventaPK other = (DetalleventaPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idTicket != other.idTicket) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.DetalleventaPK[ idProducto=" + idProducto + ", idTicket=" + idTicket + " ]";
    }
    
}
