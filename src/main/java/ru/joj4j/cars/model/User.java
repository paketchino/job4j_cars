package ru.joj4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String nameOne;

    @NonNull
    @Column(name = "second_name", nullable = false)
    private String nameTwo;

    @NonNull
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Car> usersCar;

    public User(int id,
                @NonNull String nameOne,
                @NonNull String nameTwo,
                @NonNull String login,
                @NonNull String password) {
        this.id = id;
        this.nameOne = nameOne;
        this.nameTwo = nameTwo;
        this.login = login;
        this.password = password;
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
        return "User{" + "id=" + id + ", nameOne='" + nameOne + '\''
                + ", nameTwo='" + nameTwo + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
