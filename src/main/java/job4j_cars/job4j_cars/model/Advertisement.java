package job4j_cars.job4j_cars.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "advertisements")
@Getter
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
    private byte photo;

    public Advertisement() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return id == that.id
                && isCell == that.isCell
                && photo == that.photo
                && Objects.equals(header, that.header)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, header, description, isCell, photo);
    }
}
