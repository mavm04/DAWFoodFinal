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
import Models.Ticket;
import Models.Tpv;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControllers.exceptions.NonexistentEntityException;

/**
 *
 * @author miguel
 */
public class TpvJpaController implements Serializable {

    public TpvJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tpv tpv) {
        if (tpv.getTicketCollection() == null) {
            tpv.setTicketCollection(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : tpv.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            tpv.setTicketCollection(attachedTicketCollection);
            em.persist(tpv);
            for (Ticket ticketCollectionTicket : tpv.getTicketCollection()) {
                Tpv oldIdTpvOfTicketCollectionTicket = ticketCollectionTicket.getIdTpv();
                ticketCollectionTicket.setIdTpv(tpv);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldIdTpvOfTicketCollectionTicket != null) {
                    oldIdTpvOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldIdTpvOfTicketCollectionTicket = em.merge(oldIdTpvOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tpv tpv) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tpv persistentTpv = em.find(Tpv.class, tpv.getIdTpv());
            Collection<Ticket> ticketCollectionOld = persistentTpv.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = tpv.getTicketCollection();
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            tpv.setTicketCollection(ticketCollectionNew);
            tpv = em.merge(tpv);
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    ticketCollectionOldTicket.setIdTpv(null);
                    ticketCollectionOldTicket = em.merge(ticketCollectionOldTicket);
                }
            }
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    Tpv oldIdTpvOfTicketCollectionNewTicket = ticketCollectionNewTicket.getIdTpv();
                    ticketCollectionNewTicket.setIdTpv(tpv);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldIdTpvOfTicketCollectionNewTicket != null && !oldIdTpvOfTicketCollectionNewTicket.equals(tpv)) {
                        oldIdTpvOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldIdTpvOfTicketCollectionNewTicket = em.merge(oldIdTpvOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tpv.getIdTpv();
                if (findTpv(id) == null) {
                    throw new NonexistentEntityException("The tpv with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tpv tpv;
            try {
                tpv = em.getReference(Tpv.class, id);
                tpv.getIdTpv();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tpv with id " + id + " no longer exists.", enfe);
            }
            Collection<Ticket> ticketCollection = tpv.getTicketCollection();
            for (Ticket ticketCollectionTicket : ticketCollection) {
                ticketCollectionTicket.setIdTpv(null);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
            }
            em.remove(tpv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tpv> findTpvEntities() {
        return findTpvEntities(true, -1, -1);
    }

    public List<Tpv> findTpvEntities(int maxResults, int firstResult) {
        return findTpvEntities(false, maxResults, firstResult);
    }

    private List<Tpv> findTpvEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tpv.class));
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

    public Tpv findTpv(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tpv.class, id);
        } finally {
            em.close();
        }
    }

    public int getTpvCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tpv> rt = cq.from(Tpv.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
