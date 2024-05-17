/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpaControllers;

import conexion.Detalleventa;
import conexion.DetalleventaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import conexion.Productos;
import conexion.Ticket;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControllers.exceptions.NonexistentEntityException;
import jpaControllers.exceptions.PreexistingEntityException;

/**
 *
 * @author miguel
 */
public class DetalleventaJpaController implements Serializable {

    public DetalleventaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleventa detalleventa) throws PreexistingEntityException, Exception {
        if (detalleventa.getDetalleventaPK() == null) {
            detalleventa.setDetalleventaPK(new DetalleventaPK());
        }
        detalleventa.getDetalleventaPK().setIdProducto(detalleventa.getProductos().getIdProducto());
        detalleventa.getDetalleventaPK().setIdTicket(detalleventa.getTicket().getIdTicket());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productos = detalleventa.getProductos();
            if (productos != null) {
                productos = em.getReference(productos.getClass(), productos.getIdProducto());
                detalleventa.setProductos(productos);
            }
            Ticket ticket = detalleventa.getTicket();
            if (ticket != null) {
                ticket = em.getReference(ticket.getClass(), ticket.getIdTicket());
                detalleventa.setTicket(ticket);
            }
            em.persist(detalleventa);
            if (productos != null) {
                productos.getDetalleventaCollection().add(detalleventa);
                productos = em.merge(productos);
            }
            if (ticket != null) {
                ticket.getDetalleventaCollection().add(detalleventa);
                ticket = em.merge(ticket);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleventa(detalleventa.getDetalleventaPK()) != null) {
                throw new PreexistingEntityException("Detalleventa " + detalleventa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleventa detalleventa) throws NonexistentEntityException, Exception {
        detalleventa.getDetalleventaPK().setIdProducto(detalleventa.getProductos().getIdProducto());
        detalleventa.getDetalleventaPK().setIdTicket(detalleventa.getTicket().getIdTicket());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleventa persistentDetalleventa = em.find(Detalleventa.class, detalleventa.getDetalleventaPK());
            Productos productosOld = persistentDetalleventa.getProductos();
            Productos productosNew = detalleventa.getProductos();
            Ticket ticketOld = persistentDetalleventa.getTicket();
            Ticket ticketNew = detalleventa.getTicket();
            if (productosNew != null) {
                productosNew = em.getReference(productosNew.getClass(), productosNew.getIdProducto());
                detalleventa.setProductos(productosNew);
            }
            if (ticketNew != null) {
                ticketNew = em.getReference(ticketNew.getClass(), ticketNew.getIdTicket());
                detalleventa.setTicket(ticketNew);
            }
            detalleventa = em.merge(detalleventa);
            if (productosOld != null && !productosOld.equals(productosNew)) {
                productosOld.getDetalleventaCollection().remove(detalleventa);
                productosOld = em.merge(productosOld);
            }
            if (productosNew != null && !productosNew.equals(productosOld)) {
                productosNew.getDetalleventaCollection().add(detalleventa);
                productosNew = em.merge(productosNew);
            }
            if (ticketOld != null && !ticketOld.equals(ticketNew)) {
                ticketOld.getDetalleventaCollection().remove(detalleventa);
                ticketOld = em.merge(ticketOld);
            }
            if (ticketNew != null && !ticketNew.equals(ticketOld)) {
                ticketNew.getDetalleventaCollection().add(detalleventa);
                ticketNew = em.merge(ticketNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleventaPK id = detalleventa.getDetalleventaPK();
                if (findDetalleventa(id) == null) {
                    throw new NonexistentEntityException("The detalleventa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleventaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleventa detalleventa;
            try {
                detalleventa = em.getReference(Detalleventa.class, id);
                detalleventa.getDetalleventaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleventa with id " + id + " no longer exists.", enfe);
            }
            Productos productos = detalleventa.getProductos();
            if (productos != null) {
                productos.getDetalleventaCollection().remove(detalleventa);
                productos = em.merge(productos);
            }
            Ticket ticket = detalleventa.getTicket();
            if (ticket != null) {
                ticket.getDetalleventaCollection().remove(detalleventa);
                ticket = em.merge(ticket);
            }
            em.remove(detalleventa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleventa> findDetalleventaEntities() {
        return findDetalleventaEntities(true, -1, -1);
    }

    public List<Detalleventa> findDetalleventaEntities(int maxResults, int firstResult) {
        return findDetalleventaEntities(false, maxResults, firstResult);
    }

    private List<Detalleventa> findDetalleventaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleventa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detalleventa findDetalleventa(DetalleventaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleventa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleventaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleventa> rt = cq.from(Detalleventa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
