package job4j_cars.job4j_cars.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bodyCars")
@Getter
@Setter
@AllArgsConstructor
public class BodyCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "carcase")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyCar bodyCar = (BodyCar) o;
        return id == bodyCar.id && Objects.equals(name, bodyCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public BodyCar() {

    }

}
