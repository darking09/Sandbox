/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.Tecnologiaxinstancia;
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
public class TecnologiaxinstanciaJpaController implements Serializable {

    public TecnologiaxinstanciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tecnologiaxinstancia tecnologiaxinstancia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tecnologiaxinstancia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tecnologiaxinstancia tecnologiaxinstancia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tecnologiaxinstancia = em.merge(tecnologiaxinstancia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tecnologiaxinstancia.getId();
                if (findTecnologiaxinstancia(id) == null) {
                    throw new NonexistentEntityException("The tecnologiaxinstancia with id " + id + " no longer exists.");
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
            Tecnologiaxinstancia tecnologiaxinstancia;
            try {
                tecnologiaxinstancia = em.getReference(Tecnologiaxinstancia.class, id);
                tecnologiaxinstancia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tecnologiaxinstancia with id " + id + " no longer exists.", enfe);
            }
            em.remove(tecnologiaxinstancia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tecnologiaxinstancia> findTecnologiaxinstanciaEntities() {
        return findTecnologiaxinstanciaEntities(true, -1, -1);
    }

    public List<Tecnologiaxinstancia> findTecnologiaxinstanciaEntities(int maxResults, int firstResult) {
        return findTecnologiaxinstanciaEntities(false, maxResults, firstResult);
    }

    private List<Tecnologiaxinstancia> findTecnologiaxinstanciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tecnologiaxinstancia.class));
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

    public Tecnologiaxinstancia findTecnologiaxinstancia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tecnologiaxinstancia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTecnologiaxinstanciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tecnologiaxinstancia> rt = cq.from(Tecnologiaxinstancia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
