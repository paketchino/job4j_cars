package jo4j_cars.storageRepository;

import jo4j_cars.model.Car;
import jo4j_cars.model.User;
import jo4j_cars.storageRepository.interfacerepository.DefaultMethod;
import jo4j_cars.storageRepository.interfacerepository.UserRepositoryInterface;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Repository
public class UserRepository implements UserRepositoryInterface, DefaultMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAllForTest() {
        return tx(session -> session.createQuery("from User").list(), sessionFactory);
    }

    @Override
    public Optional<User> addUser(User user) {
        LOGGER.info("Начало добавления");
        Optional<User> optionalUser = Optional.empty();
        User u = tx(session -> {
            session.save(user);
            return user;
        }, sessionFactory);

        if (user.getId() != 0) {
            optionalUser = Optional.of(u);
            LOGGER.info("Добавления произведено");
        }
        return optionalUser;
    }

    @Override
    public List<Car> findAllCarByUserId(User user) {
        return tx(session ->
                session.createQuery("select distinct u from User u join fetch u.usersCar where u.id =: uId")
                        .setParameter("uId", user.getId())
                        .list(),
                sessionFactory);
    }

    @Override
    public Optional<User> findByIdUser(int id) {
        LOGGER.info("Начат поиск пользователя по Id");
        return tx(session ->
                session.createQuery("from jo4j_cars.model.User u where u.id =: uId")
                        .setParameter("uId", id).uniqueResultOptional(), sessionFactory);
    }

    @Override
    public boolean updateUserFirstNameAndSecondName(User user) {
        return tx(session -> session.createQuery("update User as u set u.nameOne =:uNameFirst, u.nameTwo =:uNameSecond where u.id =:uId")
                .setParameter("uNameFirst", user.getNameOne())
                .setParameter("uNameSecond", user.getNameTwo())
                .setParameter("uId", user.getId())
                .executeUpdate() > 0, sessionFactory);
    }

    @Override
    public boolean updatePasswordUser(User user) {
        return tx(session -> session.createQuery("update User u set u.password =:uPassword where u.id =:uId")
                .setParameter("uPassword", user.getPassword())
                .setParameter("uId", user.getId()).executeUpdate() > 0, sessionFactory);
    }

    @Override
    public boolean updateLogin(User user) {
        return tx(session -> session.createQuery("update User u set u.login =:uLogin where u.id =:uId")
                .setParameter("uLogin", user.getLogin())
                .setParameter("uId", user.getId()).executeUpdate() > 0, sessionFactory);
    }

    @Override
    public Optional<User> findLoginAndPassword(String login, String password) {
        return tx(session -> session.createQuery("from User u where u.login =:uLogin and u.password= :uPassword")
                .setParameter("uLogin", login)
                .setParameter("uPassword", password)
                .uniqueResultOptional(), sessionFactory);
    }
}
