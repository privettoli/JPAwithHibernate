package learning.jpa.with.hibernate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
// Optional Embeddable
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {
    private String city;
    private String street;
    private Integer building;
}
