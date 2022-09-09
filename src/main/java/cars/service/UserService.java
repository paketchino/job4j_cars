package cars.service;

import cars.model.Car;
import cars.model.User;
import cars.service.interfaceservice.UserServiceInterface;
import cars.storagerepository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public List<Car> findAllCarByUserId(User user) {
        return userRepository.findAllCarByUserId(user);
    }

    @Override
    public Optional<User> findByIdUser(int id) {
        return userRepository.findByIdUser(id);
    }

    @Override
    public boolean updateUserFirstNameAndSecondName(User user) {
        return userRepository.updateUserFirstNameAndSecondName(user);
    }

    @Override
    public boolean updatePasswordUser(User user) {
        return userRepository.updatePasswordUser(user);
    }

    @Override
    public boolean updateLogin(User user) {
        return userRepository.updatePasswordUser(user);
    }

    @Override
    public Optional<User> findLoginAndPassword(String login, String password) {
        return userRepository.findLoginAndPassword(login, password);
    }

    @Override
    public List<User> users() {
        return userRepository.usersForAdmin();
    }
}