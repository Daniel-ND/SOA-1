package repository;

import entity.Coordinates;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CoordinatesRepository {
    private final Session session;
    private final EntityManager em;

    public CoordinatesRepository() {
        session = HibernateUtil.getSessionFactory().openSession();
        em = session.getEntityManagerFactory().createEntityManager();
    }

    public Coordinates findById(Integer id) {
        System.out.println("id: " + id);
        return (Coordinates) session.get(Coordinates.class, id);
    }

    public List<Coordinates> findAll() {
        return session.createQuery("from Coordinates").getResultList();
    }

    public void create(Coordinates coordinates) {
        em.getTransaction().begin();
        em.persist(coordinates);
        em.getTransaction().commit();
        em.clear();
    }

    public void update(Coordinates coordinates) {
        em.getTransaction().begin();
        em.merge(coordinates);
        em.getTransaction().commit();
        em.clear();
    }

    public void delete(Coordinates coordinates) {
        em.getTransaction().begin();
        em.remove(coordinates);
        em.getTransaction().commit();
        em.clear();
    }
}
