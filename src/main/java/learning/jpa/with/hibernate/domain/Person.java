package learning.jpa.with.hibernate.domain;

import learning.jpa.with.hibernate.converter.DateConverter;
import lombok.*;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/*@Table(name = "persons", uniqueConstraints = {
        @UniqueConstraint(name = "NamePersonCity", columnNames = {"name", "person_city"})
})*/

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends AbstractEntity implements Comparable<Person> {
    @NonNull
    private String name;
    @NonNull
    @Embedded
    /*@AttributeOverrides(
            @AttributeOverride(name = "city", column = @Column(name = "person_city"))
    )*/
    private Address address;
    @NonNull
    @Convert(converter = DateConverter.class)
    private ZonedDateTime birthday;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Transient
    private String details;
    @Version
    private Integer version;
    @SortNatural
    @JoinTable(name = "friends")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Person> friends;

    @Override
    public int compareTo(Person another) {
        return name.compareTo(another.getName());
    }
 }
