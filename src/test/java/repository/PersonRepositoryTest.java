package repository;

import learning.jpa.with.hibernate.Application;
import learning.jpa.with.hibernate.domain.Address;
import learning.jpa.with.hibernate.domain.Gender;
import learning.jpa.with.hibernate.domain.Person;
import learning.jpa.with.hibernate.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;

import static java.time.LocalDate.of;
import static java.time.ZoneId.systemDefault;
import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.of;
import static java.util.Arrays.asList;
import static learning.jpa.with.hibernate.domain.Gender.MALE;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ConfigurationProperties("test.properties")
@TransactionConfiguration(defaultRollback = false)
@SpringApplicationConfiguration(classes = Application.class)
public class PersonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private CrudRepository<Person, Long> personRepository;

    @Test
    public void save() {
        Person person = getAnatoly();
        personRepository.save(person);
        assertThat(personRepository.findOne(personRepository.count()), is(person));
    }

    public static Person getAnatoly() {
        Address anatolyAddress = new Address("Kyiv", "Zhulyans'ka", 75);
        Person anatoly = new Person("Anatoly Papenko", anatolyAddress, of(1995, Month.JULY.getValue(), 13, 0, 0, 0, 0, systemDefault()), MALE);
        anatoly.setDetails("Fucking geek");
        /*Person andrey = anatoly.clone();
        andrey.setName("Andrey Nagornyi");
        Person vasyliy = anatoly.clone();
        andrey.setName("Vasyliy Perepichay");
        Person aleksey = anatoly.clone();
        andrey.setName("Alexey Malkevich");
        List<Person> hisFriends = asList(andrey, aleksey, vasyliy);
        anatoly.setFriends(hisFriends);*/
        return anatoly;
    }

    @Test
    public void testTransient() {
        save();
        Person person = personRepository.findOne(personRepository.count());
        person.setDetails("I changed this");
        personRepository.save(person);
        //assertThat(personRepository.findOne(personRepository.count()).getDetails(), is(""));
    }

    @Test
    public void testVersion() {
        save();
        Person person = personRepository.findOne(personRepository.count());
        Address address = person.getAddress();
        address.setStreet("Dovzhenko");
        address.setBuilding(2);
        personRepository.save(person);
        assertThat(person.getVersion(), is(not(personRepository.findOne(personRepository.count()).getVersion())));
    }
}
