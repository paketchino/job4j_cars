package job4j_cars.job4j_cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "car_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "mark_id", foreignKey = @ForeignKey(name = "MARK_ID_FK"))
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "bodyCar_id", foreignKey = @ForeignKey(name = "BODYCAR_ID_FK"))
    private BodyCar bodyCar;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private Set<User> drivers = new HashSet<>();

    public Car() {

    }

}
