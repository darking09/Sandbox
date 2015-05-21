/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.Proyectoxusuario;
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
public class ProyectoxusuarioJpaController implements Serializable {

    public ProyectoxusuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyectoxusuario proyectoxusuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proyectoxusuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyectoxusuario proyectoxusuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proyectoxusuario = em.merge(proyectoxusuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proyectoxusuario.getId();
                if (findProyectoxusuario(id) == null) {
                    throw new NonexistentEntityException("The proyectoxusuario with id " + id + " no longer exists.");
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
            Proyectoxusuario proyectoxusuario;
            try {
                proyectoxusuario = em.getReference(Proyectoxusuario.class, id);
                proyectoxusuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyectoxusuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(proyectoxusuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyectoxusuario> findProyectoxusuarioEntities() {
        return findProyectoxusuarioEntities(true, -1, -1);
    }

    public List<Proyectoxusuario> findProyectoxusuarioEntities(int maxResults, int firstResult) {
        return findProyectoxusuarioEntities(false, maxResults, firstResult);
    }

    private List<Proyectoxusuario> findProyectoxusuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyectoxusuario.class));
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

    public Proyectoxusuario findProyectoxusuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyectoxusuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoxusuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyectoxusuario> rt = cq.from(Proyectoxusuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
