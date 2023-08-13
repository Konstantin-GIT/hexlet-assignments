package exercise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Data
@Entity
@Table(name="article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(unique = true)
    private String name;
    private String body;
    @ManyToOne
    private Category category;


}
// END
