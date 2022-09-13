package cars.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bodyCars")
@Getter
@EqualsAndHashCode
@ToString
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BodyCar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true, nullable = false, updatable = false)
    private String name;

}
