package ru.joj4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "cars")
@AllArgsConstructor
@Setter
@Getter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "mark_id", foreignKey = @ForeignKey(name = "MARK_ID_FK"))
    private Mark mark;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "car_bodies_id", foreignKey = @ForeignKey(name = "CAR_BODIES_ID_FK"))
    private BodyCar bodyCar;

    @NonNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private Set<User> drivers = new HashSet<>();

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
