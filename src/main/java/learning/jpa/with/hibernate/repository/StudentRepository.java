package learning.jpa.with.hibernate.repository;

import learning.jpa.with.hibernate.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
