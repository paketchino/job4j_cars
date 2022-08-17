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

    @JoinColumn(name = "header")
    private String header;

    @JoinColumn(name = "desciption_ad")
    private String description;

    @JoinColumn(name = "is_cell")
    private boolean isCell;

    @JoinColumn(name = "photo_car")
    private byte[] photo;

    @ManyToOne()
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"))
    private Car cars;

    public Advertisement() {

    }

}
