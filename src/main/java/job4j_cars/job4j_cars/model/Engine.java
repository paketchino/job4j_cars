package job4j_cars.job4j_cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@ToString
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "engines")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "engine_name")
    private String nameEngine;

    public Engine() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id
                && Objects.equals(nameEngine, engine.nameEngine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEngine);
    }
}
