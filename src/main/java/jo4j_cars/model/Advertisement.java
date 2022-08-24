package jo4j_cars.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Table(name = "advertisements")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Setter
public class Advertisement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JoinColumn(name = "header")
    private String header;

    @NonNull
    @JoinColumn(name = "desciption_ad")
    private String description;

    @NonNull
    @JoinColumn(name = "is_cell")
    private boolean isCell;

    @NonNull
    @JoinColumn(name = "photo_car")
    private byte[] photo;

    @NonNull
    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private Date created;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USERID_ID_FK"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "bodyCar_id", foreignKey = @ForeignKey(name = "BODYCAR_ID_FK"))
    private BodyCar bodyCar;

    @ManyToOne
    @JoinColumn(name = "mark_id", foreignKey = @ForeignKey(name = "MARK_ID_FK"))
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

}
