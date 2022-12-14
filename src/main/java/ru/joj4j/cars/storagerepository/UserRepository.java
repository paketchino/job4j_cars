package ru.joj4j.cars.storagerepository;

import ru.joj4j.cars.model.Car;
import ru.joj4j.cars.model.User;
import ru.joj4j.cars.storagerepository.interfacerepository.DefaultMethod;
import ru.joj4j.cars.storagerepository.interfacerepository.UserRepositoryInterface;
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
    public List<User> findAll() {
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
        LOGGER.info("Начало поиска юзера по id");
        return tx(session ->
                session.createQuery("select distinct u from "
                                + "User u join fetch u.usersCar where u.id =: uId")
                        .setParameter("uId", user.getId())
                        .list(), sessionFactory);
    }

    @Override
    public Optional<User> findByIdUser(int id) {
        LOGGER.info("Начат поиск пользователя по Id");
        return tx(session ->
                session.createQuery("from User u where u.id =: uId")
                        .setParameter("uId", id).uniqueResultOptional(), sessionFactory);
    }

    @Override
    public boolean updateUserFirstNameAndSecondName(User user) {
        LOGGER.info("Начало поиска юзера по имени и фамилии");
        return tx(session ->
                session.createQuery("update User as u set u.nameOne =:uNameFirst, "
                                + "u.nameTwo =:uNameSecond where u.id =:uId")
                .setParameter("uNameFirst", user.getNameOne())
                .setParameter("uNameSecond", user.getNameTwo())
                .setParameter("uId", user.getId())
                .executeUpdate() > 0, sessionFactory);
    }

    @Override
    public boolean updatePasswordUser(User user) {
        LOGGER.info("Обновление пароля");
        return tx(session ->
                session.createQuery("update User u set u.password =:uPassword where u.id =:uId")
                .setParameter("uPassword", user.getPassword())
                .setParameter("uId", user.getId()).executeUpdate() > 0, sessionFactory);
    }

    @Override
    public boolean updateLogin(User user) {
        LOGGER.info("Обновление по логину");
        return tx(session -> session.
                createQuery("update User u set u.login =:uLogin where u.id =:uId")
                .setParameter("uLogin", user.getLogin())
                .setParameter("uId", user.getId()).executeUpdate() > 0, sessionFactory);
    }

    @Override
    public Optional<User> findLoginAndPassword(String login, String password) {
        LOGGER.info("Поиск по id и password");
        return tx(session ->
                session.createQuery("from User u where u.login =:uLogin and u.password= :uPassword")
                .setParameter("uLogin", login)
                .setParameter("uPassword", password)
                .uniqueResultOptional(), sessionFactory);
    }

    @Override
    public List<User> usersForAdmin() {
        LOGGER.info("Поиск по user для admin");
        return tx(session -> session.createQuery("from User").list(), sessionFactory);
    }

    @Override
    public boolean deleteForTest() {
        LOGGER.info("Удаление user");
        return tx(session ->
                session.createQuery("delete User cascade").executeUpdate() > 0, sessionFactory);
    }
}
