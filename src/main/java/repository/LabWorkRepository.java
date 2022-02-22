package repository;

import dto.FilterValuesDTO;
import entity.LabWork;
import org.hibernate.Filter;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class LabWorkRepository {

    private final Session session;
    private final EntityManager em;

    public LabWorkRepository() {
        session = HibernateUtil.getSessionFactory().openSession();
        em = session.getEntityManagerFactory().createEntityManager();
    }

    public LabWork findById(Integer id) {
        return session.get(LabWork.class, id);
    }

    public List<LabWork> findAll() {
        List<LabWork> labWorks = session.createQuery("from LabWork").getResultList();
        return labWorks;
    }

    public void setFilters(FilterValuesDTO filterValues) {
        if (filterValues == null)
            return;
        if (filterValues.getId() != null && !filterValues.getId().isEmpty()) {
            Filter filter = session.enableFilter("idFilter");
            filter.setParameter("id", Integer.parseInt(filterValues.getId()));
        }
        if (filterValues.getName() != null && !filterValues.getName().isEmpty()) {
            Filter filter = session.enableFilter("nameFilter");
            filter.setParameter("name", filterValues.getName());
        }
        if (filterValues.getCoordinate_id() != null && !filterValues.getCoordinate_id().isEmpty()) {
            Filter filter = session.enableFilter("coordinateIdFilter");
            filter.setParameter("coordinate_id", Integer.parseInt(filterValues.getCoordinate_id()));
        }
        if (filterValues.getMinimalPoint() != null && !filterValues.getMinimalPoint().isEmpty()) {
            Filter filter = session.enableFilter("minimalPointFilter");
            filter.setParameter("minimalPoint", Double.parseDouble(filterValues.getMinimalPoint()));
        }
        if (filterValues.getDifficulty() != null && !filterValues.getDifficulty().isEmpty()) {
            Filter filter = session.enableFilter("difficultyFilter");
            filter.setParameter("difficulty", filterValues.getDifficulty());
        }
        if (filterValues.getPerson_id() != null && !filterValues.getPerson_id().isEmpty()) {
            Filter filter = session.enableFilter("authorFilter");
            filter.setParameter("person_id", Integer.parseInt(filterValues.getPerson_id()));
        }
    }

    public void disableFilters() {
        session.disableFilter("idFilter");
        session.disableFilter("nameFilter");
        session.disableFilter("coordinateIdFilter");
        session.disableFilter("minimalPointFilter");
        session.disableFilter("difficultyFilter");
        session.disableFilter("authorFilter");

    }

    public void create(LabWork labWork) {
        em.getTransaction().begin();
        em.persist(labWork);
        em.getTransaction().commit();
        em.clear();
    }

    public void update(LabWork labWork) {
        em.getTransaction().begin();
        em.merge(labWork);
        em.getTransaction().commit();
        em.clear();
    }

    public void delete(LabWork labWork) {
        em.getTransaction().begin();
        em.remove(labWork);
        em.getTransaction().commit();
        em.clear();
    }

    public Long countMinimalPointGreaterThan(Double minimalPoint){
        String query = "SELECT count(*) from LabWork where minimalPoint > ?1";
        return (Long) session.createQuery(query).setParameter(1, minimalPoint).getSingleResult();
    }
}
