package job4j_cars.job4j_cars.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carcase_cars")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CarcaseCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "carcase")
    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisement> advertisements;

    public CarcaseCar() {

    }
}
