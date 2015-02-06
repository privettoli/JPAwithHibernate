package repository;

import learning.jpa.with.hibernate.Application;
import learning.jpa.with.hibernate.domain.Person;
import learning.jpa.with.hibernate.repository.CustomPersonRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;

import static java.lang.String.format;
import static java.nio.file.FileSystems.getDefault;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.exists;
import static java.util.Arrays.asList;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@ConfigurationProperties("test.properties")
@SpringApplicationConfiguration(classes = Application.class)
public class CustomPersonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private CustomPersonRepository personRepository;

    @BeforeClass
    public static void beforeClass() throws IOException {
        Path path = getDefault().getPath("/Users/anatoly-home-air/persons.txt");
        if (!exists(path)) {
            createFile(path);
        }
        System.setOut(new PrintStream(path.toFile()));
    }

    @Before
    @org.springframework.transaction.annotation.Transactional
    public void createAll() {
        Person anatoly = PersonRepositoryTest.getAnatoly();
        Person anatoly2 = PersonRepositoryTest.getAnatoly();
        anatoly2.setName("Funny guy");
        Person anatoly3 = PersonRepositoryTest.getAnatoly();
        anatoly3.setName("Looool");
        Person anatoly4 = PersonRepositoryTest.getAnatoly();
        anatoly4.setName("Perdiuk Perdiukovich");
        personRepository.save(asList(anatoly, anatoly2, anatoly3, anatoly4));
    }

    @Test
    public void testFindAll() {
        List<Person> all = personRepository.findAll();
        System.out.println(format("There are %d persons", all.size()));
        all.parallelStream().forEach(System.out::println);
    }
}
