package cars.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@RequiredArgsConstructor
@Table(name = "advertisements")
@Getter
@NoArgsConstructor
@Setter
public class Advertisement implements Serializable {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name = "header")
    private String header;

    @NonNull
    @JoinColumn(name = "description_ad")
    private String description;

    @NonNull
    @JoinColumn(name = "is_cell")
    private boolean isCell;

    @NonNull
    @JoinColumn(name = "photo_car")
    private byte[] photo;

    @NonNull
    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private LocalDateTime created;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USERID_ID_FK"))
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "bodyCar_id", foreignKey = @ForeignKey(name = "BODYCAR_ID_FK"))
    private BodyCar bodyCar;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "mark_id", foreignKey = @ForeignKey(name = "MARK_ID_FK"))
    private Mark mark;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "advertisements_engines", joinColumns = {
            @JoinColumn(name = "advertisement_id", nullable = false, updatable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "engine_id", nullable = false, insertable = false)
    })
    private Set<Engine> enginesSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "advertisements_marks", joinColumns = {
            @JoinColumn(name = "advertisement_id", nullable = false, updatable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "mark_id", nullable = false, insertable = false)
    })
    private Set<Mark> markSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "advertisements_bodyCars", joinColumns = {
            @JoinColumn(name = "advertisement_id", nullable = false, updatable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "bodyCar_id", nullable = false, insertable = false)
    })
    private Set<BodyCar> bodyCarSet = new HashSet<>();

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
                && bodyCar.equals(that.bodyCar)
                && mark.equals(that.mark)
                && engine.equals(that.engine);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, header, description,
                isCell, created, user, bodyCar, mark, engine);
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
                + user + ", bodyCar=" + bodyCar + ", mark=" + mark + ", engine="
                + engine + '}';
    }
}
