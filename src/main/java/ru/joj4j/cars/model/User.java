package ru.joj4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_cars", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private Set<Car> cars = new HashSet<>();

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

    public User(@NonNull String nameOne,
                @NonNull String nameTwo,
                @NonNull String login,
                @NonNull String password,
                Set<Car> cars) {
        this.nameOne = nameOne;
        this.nameTwo = nameTwo;
        this.login = login;
        this.password = password;
        this.cars = cars;
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
