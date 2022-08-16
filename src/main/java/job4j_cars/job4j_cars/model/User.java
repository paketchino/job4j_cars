package job4j_cars.job4j_cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JoinColumn(name = "author_first_name")
    private String nameOne;

    @JoinColumn(name = "author_second_name")
    private String nameTwo;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisement> advertisements;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Car> cars;

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(nameOne, user.nameOne)
                && Objects.equals(nameTwo, user.nameTwo)
                && Objects.equals(advertisements, user.advertisements)
                && Objects.equals(cars, user.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOne, nameTwo, advertisements, cars);
    }
}
