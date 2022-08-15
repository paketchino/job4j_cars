package job4j_cars.job4j_cars.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "marks")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "marks")
    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisement> advertisements;

    public Mark() {

    }
}
