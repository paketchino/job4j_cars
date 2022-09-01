package jo4j_cars.model;


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
    @JoinColumn(name = "carcase")
    private String bodyName;

}
