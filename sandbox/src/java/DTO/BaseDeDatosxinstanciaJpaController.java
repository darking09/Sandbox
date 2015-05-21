/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.BaseDeDatosxinstancia;
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
public class BaseDeDatosxinstanciaJpaController implements Serializable {

    public BaseDeDatosxinstanciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BaseDeDatosxinstancia baseDeDatosxinstancia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(baseDeDatosxinstancia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BaseDeDatosxinstancia baseDeDatosxinstancia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            baseDeDatosxinstancia = em.merge(baseDeDatosxinstancia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = baseDeDatosxinstancia.getId();
                if (findBaseDeDatosxinstancia(id) == null) {
                    throw new NonexistentEntityException("The baseDeDatosxinstancia with id " + id + " no longer exists.");
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
            BaseDeDatosxinstancia baseDeDatosxinstancia;
            try {
                baseDeDatosxinstancia = em.getReference(BaseDeDatosxinstancia.class, id);
                baseDeDatosxinstancia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baseDeDatosxinstancia with id " + id + " no longer exists.", enfe);
            }
            em.remove(baseDeDatosxinstancia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BaseDeDatosxinstancia> findBaseDeDatosxinstanciaEntities() {
        return findBaseDeDatosxinstanciaEntities(true, -1, -1);
    }

    public List<BaseDeDatosxinstancia> findBaseDeDatosxinstanciaEntities(int maxResults, int firstResult) {
        return findBaseDeDatosxinstanciaEntities(false, maxResults, firstResult);
    }

    private List<BaseDeDatosxinstancia> findBaseDeDatosxinstanciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaseDeDatosxinstancia.class));
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

    public BaseDeDatosxinstancia findBaseDeDatosxinstancia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaseDeDatosxinstancia.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaseDeDatosxinstanciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaseDeDatosxinstancia> rt = cq.from(BaseDeDatosxinstancia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
