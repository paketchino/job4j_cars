package job4j_cars.job4j_cars.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bodyCars")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class BodyCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "carcase")
    private String name;

    public BodyCar() {

    }

}
