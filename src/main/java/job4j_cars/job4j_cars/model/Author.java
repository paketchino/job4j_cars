package job4j_cars.job4j_cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
public class Author {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JoinColumn(name = "author_first_name")
    private String nameOne;

    @JoinColumn(name = "author_second_name")
    private String nameTwo;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisement> advertisements;

    public Author() {

    }

}
