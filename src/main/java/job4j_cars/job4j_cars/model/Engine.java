package job4j_cars.job4j_cars.model;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
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

}
