package cars.model;

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
    @Column(unique = true, nullable = false, updatable = false)
    private String name;

}
