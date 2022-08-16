package job4j_cars.job4j_cars.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marks")
@Getter
@Setter
@AllArgsConstructor
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "marks")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mark mark = (Mark) o;
        return id == mark.id && Objects.equals(name, mark.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Mark() {

    }
}
