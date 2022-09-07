package cars.storagerepository.interfacerepository;

import cars.model.Car;
import cars.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface {

    List<User> findAllForTest();

    Optional<User> addUser(User user);

    List<Car> findAllCarByUserId(User user);

    Optional<User> findByIdUser(int id);

    boolean updateUserFirstNameAndSecondName(User user);

    boolean updatePasswordUser(User user);

    boolean updateLogin(User user);

    Optional<User> findLoginAndPassword(String login, String password);

    List<User> usersForAdmin();

    boolean deleteForTest();
}
