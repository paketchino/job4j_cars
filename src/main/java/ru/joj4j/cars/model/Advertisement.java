package ru.joj4j.cars.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@NamedEntityGraph
@Entity
@RequiredArgsConstructor
@Table(name = "advertisements")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Advertisement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false)
    private String header;

    @NonNull
    @Column(nullable = false)
    private String description;

    @NonNull
    @Column(nullable = false)
    private boolean isCell;

    @NonNull
    @Column
    private byte[] photo;

    @NonNull
    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private LocalDateTime created;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

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
                && header.equals(that.header)
                && description.equals(that.description)
                && Arrays.equals(photo, that.photo)
                && created.equals(that.created)
                && user.equals(that.user)
                && car.equals(that.car);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, header, description,
                isCell, created, user, car);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        return "Advertisement{" + "id=" + id
                + ", header='" + header + '\'' + ", description='"
                + description + '\''
                + ", isCell=" + isCell
                + ", photo=" + Arrays.toString(photo) + ", created="
                + created + ", user="
                + user + ", car=" + car
                + '}';
    }
}
