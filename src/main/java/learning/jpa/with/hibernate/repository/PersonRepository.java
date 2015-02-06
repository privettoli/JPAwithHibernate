package learning.jpa.with.hibernate.repository;

import learning.jpa.with.hibernate.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
