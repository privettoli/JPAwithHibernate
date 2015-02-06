package repository;

import learning.jpa.with.hibernate.Application;
import learning.jpa.with.hibernate.domain.Address;
import learning.jpa.with.hibernate.domain.Gender;
import learning.jpa.with.hibernate.domain.Student;
import learning.jpa.with.hibernate.repository.PersonRepository;
import learning.jpa.with.hibernate.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static java.time.ZoneId.systemDefault;
import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.of;
import static learning.jpa.with.hibernate.domain.Gender.MALE;

@RunWith(SpringJUnit4ClassRunner.class)
@ConfigurationProperties("test.properties")
@TransactionConfiguration(defaultRollback = false)
@SpringApplicationConfiguration(classes = Application.class)
public class StudentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private StudentRepository studentRepository;

    @Test
    public void testSave() {
        studentRepository.deleteAll();
        Student student = new Student();
        student.setName("Anatoly Papenko");
        student.setBirthday(of(1995, Month.JULY.getValue(), 13, 0, 0, 0, 0, systemDefault()));
        student.setGender(MALE);
        student.setGroup("ІС-з31");
        student.setAddress(new Address("Kyiv", "Zhulyans'ka", 75));
        studentRepository.save(student);
    }
}
