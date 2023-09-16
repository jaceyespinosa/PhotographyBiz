package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PhotoSession;

public class PhotoSessionHelper {

    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PhotographyBiz");

    public void insertSession(PhotoSession ps) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(ps);
        em.getTransaction().commit();
        em.close();
    }

    public List<PhotoSession> showAllSessions() {
        EntityManager em = emfactory.createEntityManager();
        List<PhotoSession> allSessions = em.createQuery("SELECT s FROM PhotoSession s").getResultList();
        return allSessions;
    }

    public void deleteSession(PhotoSession toDelete) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<PhotoSession> typedQuery = em.createQuery("select ps from PhotoSession ps where ps.clientName = :selectedClientName", PhotoSession.class);
        typedQuery.setParameter("selectedClientName", toDelete.getClientName());
        typedQuery.setMaxResults(1);
        PhotoSession result = typedQuery.getSingleResult();
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }

    public PhotoSession searchForSessionById(int idToEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        PhotoSession found = em.find(PhotoSession.class, idToEdit);
        em.close();
        return found;
    }

    public List<PhotoSession> searchForSessionByClientName(String clientName) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<PhotoSession> typedQuery = em.createQuery("select ps from PhotoSession ps where ps.clientName = :selectedClientName", PhotoSession.class);
        typedQuery.setParameter("selectedClientName", clientName);
        List<PhotoSession> foundSessions = typedQuery.getResultList();
        em.close();
        return foundSessions;
    }

    public void updateSession(PhotoSession toEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(toEdit);
        em.getTransaction().commit();
        em.close();
    }

    public void cleanUp() {
        emfactory.close();
    }
}


