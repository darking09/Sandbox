/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.Proyectoxmateria;
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
public class ProyectoxmateriaJpaController implements Serializable {

    public ProyectoxmateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyectoxmateria proyectoxmateria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proyectoxmateria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyectoxmateria proyectoxmateria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proyectoxmateria = em.merge(proyectoxmateria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proyectoxmateria.getId();
                if (findProyectoxmateria(id) == null) {
                    throw new NonexistentEntityException("The proyectoxmateria with id " + id + " no longer exists.");
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
            Proyectoxmateria proyectoxmateria;
            try {
                proyectoxmateria = em.getReference(Proyectoxmateria.class, id);
                proyectoxmateria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyectoxmateria with id " + id + " no longer exists.", enfe);
            }
            em.remove(proyectoxmateria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyectoxmateria> findProyectoxmateriaEntities() {
        return findProyectoxmateriaEntities(true, -1, -1);
    }

    public List<Proyectoxmateria> findProyectoxmateriaEntities(int maxResults, int firstResult) {
        return findProyectoxmateriaEntities(false, maxResults, firstResult);
    }

    private List<Proyectoxmateria> findProyectoxmateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyectoxmateria.class));
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

    public Proyectoxmateria findProyectoxmateria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyectoxmateria.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoxmateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyectoxmateria> rt = cq.from(Proyectoxmateria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
