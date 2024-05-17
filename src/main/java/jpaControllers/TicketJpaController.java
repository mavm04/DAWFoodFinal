/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpaControllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import conexion.Tpv;
import conexion.Detalleventa;
import conexion.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControllers.exceptions.IllegalOrphanException;
import jpaControllers.exceptions.NonexistentEntityException;

/**
 *
 * @author miguel
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        if (ticket.getDetalleventaCollection() == null) {
            ticket.setDetalleventaCollection(new ArrayList<Detalleventa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tpv idTpv = ticket.getIdTpv();
            if (idTpv != null) {
                idTpv = em.getReference(idTpv.getClass(), idTpv.getIdTpv());
                ticket.setIdTpv(idTpv);
            }
            Collection<Detalleventa> attachedDetalleventaCollection = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaCollectionDetalleventaToAttach : ticket.getDetalleventaCollection()) {
                detalleventaCollectionDetalleventaToAttach = em.getReference(detalleventaCollectionDetalleventaToAttach.getClass(), detalleventaCollectionDetalleventaToAttach.getDetalleventaPK());
                attachedDetalleventaCollection.add(detalleventaCollectionDetalleventaToAttach);
            }
            ticket.setDetalleventaCollection(attachedDetalleventaCollection);
            em.persist(ticket);
            if (idTpv != null) {
                idTpv.getTicketCollection().add(ticket);
                idTpv = em.merge(idTpv);
            }
            for (Detalleventa detalleventaCollectionDetalleventa : ticket.getDetalleventaCollection()) {
                Ticket oldTicketOfDetalleventaCollectionDetalleventa = detalleventaCollectionDetalleventa.getTicket();
                detalleventaCollectionDetalleventa.setTicket(ticket);
                detalleventaCollectionDetalleventa = em.merge(detalleventaCollectionDetalleventa);
                if (oldTicketOfDetalleventaCollectionDetalleventa != null) {
                    oldTicketOfDetalleventaCollectionDetalleventa.getDetalleventaCollection().remove(detalleventaCollectionDetalleventa);
                    oldTicketOfDetalleventaCollectionDetalleventa = em.merge(oldTicketOfDetalleventaCollectionDetalleventa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getIdTicket());
            Tpv idTpvOld = persistentTicket.getIdTpv();
            Tpv idTpvNew = ticket.getIdTpv();
            Collection<Detalleventa> detalleventaCollectionOld = persistentTicket.getDetalleventaCollection();
            Collection<Detalleventa> detalleventaCollectionNew = ticket.getDetalleventaCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalleventa detalleventaCollectionOldDetalleventa : detalleventaCollectionOld) {
                if (!detalleventaCollectionNew.contains(detalleventaCollectionOldDetalleventa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleventa " + detalleventaCollectionOldDetalleventa + " since its ticket field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTpvNew != null) {
                idTpvNew = em.getReference(idTpvNew.getClass(), idTpvNew.getIdTpv());
                ticket.setIdTpv(idTpvNew);
            }
            Collection<Detalleventa> attachedDetalleventaCollectionNew = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaCollectionNewDetalleventaToAttach : detalleventaCollectionNew) {
                detalleventaCollectionNewDetalleventaToAttach = em.getReference(detalleventaCollectionNewDetalleventaToAttach.getClass(), detalleventaCollectionNewDetalleventaToAttach.getDetalleventaPK());
                attachedDetalleventaCollectionNew.add(detalleventaCollectionNewDetalleventaToAttach);
            }
            detalleventaCollectionNew = attachedDetalleventaCollectionNew;
            ticket.setDetalleventaCollection(detalleventaCollectionNew);
            ticket = em.merge(ticket);
            if (idTpvOld != null && !idTpvOld.equals(idTpvNew)) {
                idTpvOld.getTicketCollection().remove(ticket);
                idTpvOld = em.merge(idTpvOld);
            }
            if (idTpvNew != null && !idTpvNew.equals(idTpvOld)) {
                idTpvNew.getTicketCollection().add(ticket);
                idTpvNew = em.merge(idTpvNew);
            }
            for (Detalleventa detalleventaCollectionNewDetalleventa : detalleventaCollectionNew) {
                if (!detalleventaCollectionOld.contains(detalleventaCollectionNewDetalleventa)) {
                    Ticket oldTicketOfDetalleventaCollectionNewDetalleventa = detalleventaCollectionNewDetalleventa.getTicket();
                    detalleventaCollectionNewDetalleventa.setTicket(ticket);
                    detalleventaCollectionNewDetalleventa = em.merge(detalleventaCollectionNewDetalleventa);
                    if (oldTicketOfDetalleventaCollectionNewDetalleventa != null && !oldTicketOfDetalleventaCollectionNewDetalleventa.equals(ticket)) {
                        oldTicketOfDetalleventaCollectionNewDetalleventa.getDetalleventaCollection().remove(detalleventaCollectionNewDetalleventa);
                        oldTicketOfDetalleventaCollectionNewDetalleventa = em.merge(oldTicketOfDetalleventaCollectionNewDetalleventa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getIdTicket();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalleventa> detalleventaCollectionOrphanCheck = ticket.getDetalleventaCollection();
            for (Detalleventa detalleventaCollectionOrphanCheckDetalleventa : detalleventaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ticket (" + ticket + ") cannot be destroyed since the Detalleventa " + detalleventaCollectionOrphanCheckDetalleventa + " in its detalleventaCollection field has a non-nullable ticket field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tpv idTpv = ticket.getIdTpv();
            if (idTpv != null) {
                idTpv.getTicketCollection().remove(ticket);
                idTpv = em.merge(idTpv);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
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

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
