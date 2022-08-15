package job4j_cars.job4j_cars.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "advertisements")
@Getter
@EqualsAndHashCode
@Setter
@AllArgsConstructor
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "desciption_ad")
    private String description;

    @ManyToOne
    @JoinColumn(name = "carcase_cars_id", foreignKey = @ForeignKey(name = "CARCASECAR_ID_FK"))
    private CarcaseCar carcaseCar;

    @ManyToOne
    @JoinColumn(name = "marks", foreignKey = @ForeignKey(name = "MARK_ID_FK"))
    private Mark mark;

    @JoinColumn(name = "is_cell")
    boolean isCell;

    @JoinColumn(name = "photo_car")
    byte photo;

    public Advertisement() {

    }
}
