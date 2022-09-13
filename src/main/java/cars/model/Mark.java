package cars.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "marks")
@Getter
@EqualsAndHashCode
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Mark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true, nullable = false, updatable = false)
    private String name;

    @Override
    public String toString() {
        return "Mark{" + "id=" + id
                + ", name='" + name
                + '\'' + '}';
    }
}
