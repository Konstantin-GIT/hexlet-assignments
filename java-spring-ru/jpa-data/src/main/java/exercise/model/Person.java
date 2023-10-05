package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Getter
@Setter
@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

}
// END
