package jo4j_cars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User implements Serializable {

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


}
