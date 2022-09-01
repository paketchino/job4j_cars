package jo4j_cars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @JoinColumn(name = "author_first_name")
    private String nameOne;

    @NonNull
    @JoinColumn(name = "author_second_name")
    private String nameTwo;

    @NonNull
    @JoinColumn(name = "login")
    private String login;

    @NonNull
    @JoinColumn(name = "password")
    private String password;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Car> usersCar;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && nameOne.equals(user.nameOne)
                && nameTwo.equals(user.nameTwo)
                && login.equals(user.login)
                && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOne, nameTwo, login, password);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nameOne='"
                + nameOne + '\'' + ", nameTwo='"
                + nameTwo + '\'' + ", login='" + login
                + '\'' + ", password='" + password
                + '\'' + '}';
    }
}
