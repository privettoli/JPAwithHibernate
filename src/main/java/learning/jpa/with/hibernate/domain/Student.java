package learning.jpa.with.hibernate.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "students")
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {
    @Column(name = "student_group")
    private String group;
}
