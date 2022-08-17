package job4j_cars.job4j_cars.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
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

    public Mark() {

    }
}
