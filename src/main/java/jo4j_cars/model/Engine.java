package jo4j_cars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "engines")
public class Engine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name = "engine_name")
    private String name;

}
