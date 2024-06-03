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
import Models.Productos;
import Models.Tipoproducto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControllers.exceptions.NonexistentEntityException;

/**
 *
 * @author migue
 */
public class TipoproductoJpaController implements Serializable {

    public TipoproductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoproducto tipoproducto) {
        if (tipoproducto.getProductosCollection() == null) {
            tipoproducto.setProductosCollection(new ArrayList<Productos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Productos> attachedProductosCollection = new ArrayList<Productos>();
            for (Productos productosCollectionProductosToAttach : tipoproducto.getProductosCollection()) {
                productosCollectionProductosToAttach = em.getReference(productosCollectionProductosToAttach.getClass(), productosCollectionProductosToAttach.getIdProducto());
                attachedProductosCollection.add(productosCollectionProductosToAttach);
            }
            tipoproducto.setProductosCollection(attachedProductosCollection);
            em.persist(tipoproducto);
            for (Productos productosCollectionProductos : tipoproducto.getProductosCollection()) {
                Tipoproducto oldIdTipoProductoOfProductosCollectionProductos = productosCollectionProductos.getIdTipoProducto();
                productosCollectionProductos.setIdTipoProducto(tipoproducto);
                productosCollectionProductos = em.merge(productosCollectionProductos);
                if (oldIdTipoProductoOfProductosCollectionProductos != null) {
                    oldIdTipoProductoOfProductosCollectionProductos.getProductosCollection().remove(productosCollectionProductos);
                    oldIdTipoProductoOfProductosCollectionProductos = em.merge(oldIdTipoProductoOfProductosCollectionProductos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoproducto tipoproducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproducto persistentTipoproducto = em.find(Tipoproducto.class, tipoproducto.getIdTipoProducto());
            Collection<Productos> productosCollectionOld = persistentTipoproducto.getProductosCollection();
            Collection<Productos> productosCollectionNew = tipoproducto.getProductosCollection();
            Collection<Productos> attachedProductosCollectionNew = new ArrayList<Productos>();
            for (Productos productosCollectionNewProductosToAttach : productosCollectionNew) {
                productosCollectionNewProductosToAttach = em.getReference(productosCollectionNewProductosToAttach.getClass(), productosCollectionNewProductosToAttach.getIdProducto());
                attachedProductosCollectionNew.add(productosCollectionNewProductosToAttach);
            }
            productosCollectionNew = attachedProductosCollectionNew;
            tipoproducto.setProductosCollection(productosCollectionNew);
            tipoproducto = em.merge(tipoproducto);
            for (Productos productosCollectionOldProductos : productosCollectionOld) {
                if (!productosCollectionNew.contains(productosCollectionOldProductos)) {
                    productosCollectionOldProductos.setIdTipoProducto(null);
                    productosCollectionOldProductos = em.merge(productosCollectionOldProductos);
                }
            }
            for (Productos productosCollectionNewProductos : productosCollectionNew) {
                if (!productosCollectionOld.contains(productosCollectionNewProductos)) {
                    Tipoproducto oldIdTipoProductoOfProductosCollectionNewProductos = productosCollectionNewProductos.getIdTipoProducto();
                    productosCollectionNewProductos.setIdTipoProducto(tipoproducto);
                    productosCollectionNewProductos = em.merge(productosCollectionNewProductos);
                    if (oldIdTipoProductoOfProductosCollectionNewProductos != null && !oldIdTipoProductoOfProductosCollectionNewProductos.equals(tipoproducto)) {
                        oldIdTipoProductoOfProductosCollectionNewProductos.getProductosCollection().remove(productosCollectionNewProductos);
                        oldIdTipoProductoOfProductosCollectionNewProductos = em.merge(oldIdTipoProductoOfProductosCollectionNewProductos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoproducto.getIdTipoProducto();
                if (findTipoproducto(id) == null) {
                    throw new NonexistentEntityException("The tipoproducto with id " + id + " no longer exists.");
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
            Tipoproducto tipoproducto;
            try {
                tipoproducto = em.getReference(Tipoproducto.class, id);
                tipoproducto.getIdTipoProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoproducto with id " + id + " no longer exists.", enfe);
            }
            Collection<Productos> productosCollection = tipoproducto.getProductosCollection();
            for (Productos productosCollectionProductos : productosCollection) {
                productosCollectionProductos.setIdTipoProducto(null);
                productosCollectionProductos = em.merge(productosCollectionProductos);
            }
            em.remove(tipoproducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoproducto> findTipoproductoEntities() {
        return findTipoproductoEntities(true, -1, -1);
    }

    public List<Tipoproducto> findTipoproductoEntities(int maxResults, int firstResult) {
        return findTipoproductoEntities(false, maxResults, firstResult);
    }

    private List<Tipoproducto> findTipoproductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoproducto.class));
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

    public Tipoproducto findTipoproducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoproducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoproductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoproducto> rt = cq.from(Tipoproducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
