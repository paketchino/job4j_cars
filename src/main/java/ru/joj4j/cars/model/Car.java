package ru.joj4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "mark_id")
    private Mark mark;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "bodyCar_id")
    private BodyCar bodyCar;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && mark.equals(car.mark)
                && engine.equals(car.engine)
                && bodyCar.equals(car.bodyCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, engine, bodyCar);
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id
                + '\'' + ", mark=" + mark + ", engine="
                + engine + ", bodyCar=" + bodyCar + '}';
    }

}
