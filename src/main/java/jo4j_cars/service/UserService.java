package jo4j_cars.service;

import jo4j_cars.model.User;
import jo4j_cars.storageRepository.UserRepository;
import jo4j_cars.storageRepository.interfacerepository.UserRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserRepositoryInterface {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllForTest() {
        return null;
    }

    @Override
    public Optional<User> addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public Optional<User> findByIdUser(int id) {
        return userRepository.findByIdUser(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public Optional<User> findLoginAndPassword(String login, String password) {
        return userRepository.findLoginAndPassword(login, password);
    }


}
