/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.BaseDeDatos;
import DTO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author asus 01
 */
public class BaseDeDatosJpaController implements Serializable {

    public BaseDeDatosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BaseDeDatos baseDeDatos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(baseDeDatos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BaseDeDatos baseDeDatos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            baseDeDatos = em.merge(baseDeDatos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = baseDeDatos.getId();
                if (findBaseDeDatos(id) == null) {
                    throw new NonexistentEntityException("The baseDeDatos with id " + id + " no longer exists.");
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
            BaseDeDatos baseDeDatos;
            try {
                baseDeDatos = em.getReference(BaseDeDatos.class, id);
                baseDeDatos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baseDeDatos with id " + id + " no longer exists.", enfe);
            }
            em.remove(baseDeDatos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BaseDeDatos> findBaseDeDatosEntities() {
        return findBaseDeDatosEntities(true, -1, -1);
    }

    public List<BaseDeDatos> findBaseDeDatosEntities(int maxResults, int firstResult) {
        return findBaseDeDatosEntities(false, maxResults, firstResult);
    }

    private List<BaseDeDatos> findBaseDeDatosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaseDeDatos.class));
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

    public BaseDeDatos findBaseDeDatos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaseDeDatos.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaseDeDatosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaseDeDatos> rt = cq.from(BaseDeDatos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
