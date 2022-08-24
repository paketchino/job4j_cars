package jo4j_cars.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
@Entity
@Table(name = "marks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Mark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name = "marks")
    private String name;

}
