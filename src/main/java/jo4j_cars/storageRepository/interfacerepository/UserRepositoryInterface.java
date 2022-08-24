package jo4j_cars.storageRepository.interfacerepository;

import jo4j_cars.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface {

    List<User> findAllForTest();

    Optional<User> addUser(User user);

    Optional<User> findByIdUser(int id);

    Optional<User> updateUser(User user);

    Optional<User> findLoginAndPassword(String login, String password);

}
