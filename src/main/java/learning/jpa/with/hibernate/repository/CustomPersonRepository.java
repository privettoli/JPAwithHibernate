package learning.jpa.with.hibernate.repository;

import learning.jpa.with.hibernate.domain.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomPersonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> query = entityManager.createQuery("SELECT person FROM Person person", Person.class);
        return query.getResultList();
    }

    public void save(List<Person> persons) {
        persons.parallelStream().forEach(entityManager::persist);
    }
}
