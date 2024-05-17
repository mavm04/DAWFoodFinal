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
import conexion.Tipoproducto;
import conexion.Detalleventa;
import conexion.Productos;
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
public class ProductosJpaController implements Serializable {

    public ProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos productos) {
        if (productos.getDetalleventaCollection() == null) {
            productos.setDetalleventaCollection(new ArrayList<Detalleventa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproducto idTipoProducto = productos.getIdTipoProducto();
            if (idTipoProducto != null) {
                idTipoProducto = em.getReference(idTipoProducto.getClass(), idTipoProducto.getIdTipoProducto());
                productos.setIdTipoProducto(idTipoProducto);
            }
            Collection<Detalleventa> attachedDetalleventaCollection = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaCollectionDetalleventaToAttach : productos.getDetalleventaCollection()) {
                detalleventaCollectionDetalleventaToAttach = em.getReference(detalleventaCollectionDetalleventaToAttach.getClass(), detalleventaCollectionDetalleventaToAttach.getDetalleventaPK());
                attachedDetalleventaCollection.add(detalleventaCollectionDetalleventaToAttach);
            }
            productos.setDetalleventaCollection(attachedDetalleventaCollection);
            em.persist(productos);
            if (idTipoProducto != null) {
                idTipoProducto.getProductosCollection().add(productos);
                idTipoProducto = em.merge(idTipoProducto);
            }
            for (Detalleventa detalleventaCollectionDetalleventa : productos.getDetalleventaCollection()) {
                Productos oldProductosOfDetalleventaCollectionDetalleventa = detalleventaCollectionDetalleventa.getProductos();
                detalleventaCollectionDetalleventa.setProductos(productos);
                detalleventaCollectionDetalleventa = em.merge(detalleventaCollectionDetalleventa);
                if (oldProductosOfDetalleventaCollectionDetalleventa != null) {
                    oldProductosOfDetalleventaCollectionDetalleventa.getDetalleventaCollection().remove(detalleventaCollectionDetalleventa);
                    oldProductosOfDetalleventaCollectionDetalleventa = em.merge(oldProductosOfDetalleventaCollectionDetalleventa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productos productos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos persistentProductos = em.find(Productos.class, productos.getIdProducto());
            Tipoproducto idTipoProductoOld = persistentProductos.getIdTipoProducto();
            Tipoproducto idTipoProductoNew = productos.getIdTipoProducto();
            Collection<Detalleventa> detalleventaCollectionOld = persistentProductos.getDetalleventaCollection();
            Collection<Detalleventa> detalleventaCollectionNew = productos.getDetalleventaCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalleventa detalleventaCollectionOldDetalleventa : detalleventaCollectionOld) {
                if (!detalleventaCollectionNew.contains(detalleventaCollectionOldDetalleventa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleventa " + detalleventaCollectionOldDetalleventa + " since its productos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoProductoNew != null) {
                idTipoProductoNew = em.getReference(idTipoProductoNew.getClass(), idTipoProductoNew.getIdTipoProducto());
                productos.setIdTipoProducto(idTipoProductoNew);
            }
            Collection<Detalleventa> attachedDetalleventaCollectionNew = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaCollectionNewDetalleventaToAttach : detalleventaCollectionNew) {
                detalleventaCollectionNewDetalleventaToAttach = em.getReference(detalleventaCollectionNewDetalleventaToAttach.getClass(), detalleventaCollectionNewDetalleventaToAttach.getDetalleventaPK());
                attachedDetalleventaCollectionNew.add(detalleventaCollectionNewDetalleventaToAttach);
            }
            detalleventaCollectionNew = attachedDetalleventaCollectionNew;
            productos.setDetalleventaCollection(detalleventaCollectionNew);
            productos = em.merge(productos);
            if (idTipoProductoOld != null && !idTipoProductoOld.equals(idTipoProductoNew)) {
                idTipoProductoOld.getProductosCollection().remove(productos);
                idTipoProductoOld = em.merge(idTipoProductoOld);
            }
            if (idTipoProductoNew != null && !idTipoProductoNew.equals(idTipoProductoOld)) {
                idTipoProductoNew.getProductosCollection().add(productos);
                idTipoProductoNew = em.merge(idTipoProductoNew);
            }
            for (Detalleventa detalleventaCollectionNewDetalleventa : detalleventaCollectionNew) {
                if (!detalleventaCollectionOld.contains(detalleventaCollectionNewDetalleventa)) {
                    Productos oldProductosOfDetalleventaCollectionNewDetalleventa = detalleventaCollectionNewDetalleventa.getProductos();
                    detalleventaCollectionNewDetalleventa.setProductos(productos);
                    detalleventaCollectionNewDetalleventa = em.merge(detalleventaCollectionNewDetalleventa);
                    if (oldProductosOfDetalleventaCollectionNewDetalleventa != null && !oldProductosOfDetalleventaCollectionNewDetalleventa.equals(productos)) {
                        oldProductosOfDetalleventaCollectionNewDetalleventa.getDetalleventaCollection().remove(detalleventaCollectionNewDetalleventa);
                        oldProductosOfDetalleventaCollectionNewDetalleventa = em.merge(oldProductosOfDetalleventaCollectionNewDetalleventa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productos.getIdProducto();
                if (findProductos(id) == null) {
                    throw new NonexistentEntityException("The productos with id " + id + " no longer exists.");
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
            Productos productos;
            try {
                productos = em.getReference(Productos.class, id);
                productos.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalleventa> detalleventaCollectionOrphanCheck = productos.getDetalleventaCollection();
            for (Detalleventa detalleventaCollectionOrphanCheckDetalleventa : detalleventaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the Detalleventa " + detalleventaCollectionOrphanCheckDetalleventa + " in its detalleventaCollection field has a non-nullable productos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tipoproducto idTipoProducto = productos.getIdTipoProducto();
            if (idTipoProducto != null) {
                idTipoProducto.getProductosCollection().remove(productos);
                idTipoProducto = em.merge(idTipoProducto);
            }
            em.remove(productos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productos> findProductosEntities() {
        return findProductosEntities(true, -1, -1);
    }

    public List<Productos> findProductosEntities(int maxResults, int firstResult) {
        return findProductosEntities(false, maxResults, firstResult);
    }

    private List<Productos> findProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos.class));
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

    public Productos findProductos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productos> rt = cq.from(Productos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
