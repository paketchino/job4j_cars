package jo4j_cars.service.interfaceservice;

import jo4j_cars.model.Car;
import jo4j_cars.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    Optional<User> addUser(User user);

    List<Car> findAllCarByUserId(User user);

    Optional<User> findByIdUser(int id);

    boolean updateUserFirstNameAndSecondName(User user);

    boolean updatePasswordUser(User user);

    boolean updateLogin(User user);

    Optional<User> findLoginAndPassword(String login, String password);

    List<User> users();
}
