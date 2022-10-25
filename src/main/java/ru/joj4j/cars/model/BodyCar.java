package ru.joj4j.cars.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car_bodies")
@Getter
@EqualsAndHashCode
@ToString
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BodyCar implements Serializable {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true, nullable = false, updatable = false)
    private String name;

}
