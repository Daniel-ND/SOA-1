package repository;

import entity.Person;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonRepository {
    private final Session session;
    private final EntityManager em;

    public PersonRepository() {
        session = HibernateUtil.getSessionFactory().openSession();
        em = session.getEntityManagerFactory().createEntityManager();
    }

    public Person findById(Integer id) {
        System.out.println("id: " + id);
        return (Person) session.get(Person.class, id);
    }

    public List<Person> findAll() {
        return session.createQuery("from Person").getResultList();
    }

    public void create(Person person) {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.clear();
    }

    public void update(Person person) {
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
        em.clear();
    }

    public void delete(Person person) {
        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
        em.clear();
    }
}
