package cars.storagerepository;

import cars.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserRepositoryTest {

    public static SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void whenAddUserThenUser() {
        User user = new User(2, "Ivan", "Ivanov", "Ivan", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        assertEquals(user, userRepository.findAllForTest().get(0));
    }

    @Test
    public void whenAddAndFindUserThenReturnUser() {
        User user = new User(3, "Sanya", "Gavrilov", "HITJAD", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById, user);
    }

    @Test
    public void whenUpdateUserLoginThenReturnUpdatesLoginsUser() {
        User user = new User(4, "Serhet", "Gavrilov", "paketchibo", "12345");
        User updateLogin = new User(4,  "Serhet", "Gavrilov", "fdgdfgcv", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById, user);
        assertTrue(userRepository.updateLogin(updateLogin));

        assertEquals(updateLogin.getLogin(), userRepository.findAllForTest().get(3).getLogin());
    }

    @Test
    public void whenUpdateUserFirstNameAndSecondNameThenReturnUpdatesDateFNAndSN() {
        User user = new User(4, "Sanya", "Gavrilov", "fdgdfgcv", "12345");
        User updateName = new User(4,  "Stephan", "Zaharev", "fdgdfgcv", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        Optional<User> findById = userRepository.findByIdUser(user.getId());

        assertEquals(findById.get().getNameOne(), user.getNameOne());
        assertEquals(findById.get().getNameTwo(), user.getNameTwo());
        userRepository.updateUserFirstNameAndSecondName(updateName);
        assertEquals(userRepository.findAllForTest().get(0).getNameOne(), updateName.getNameOne());
        assertEquals(userRepository.findAllForTest().get(0).getNameTwo(), updateName.getNameTwo());
    }

    @Test
    public void whenUpdateUserPasswordThenReturnUpdatesPassword() {
        User user = new User(4,  "Stephan", "Zaharev", "fdgdfgcv", "12345");
        User updatePassword = new User(4,  "Stephan", "Zaharev", "fdgdfgcv", "qwert");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();

        assertEquals(findById.getPassword(), user.getPassword());
        assertTrue(userRepository.updatePasswordUser(updatePassword));
        assertEquals(userRepository.findAllForTest().get(3).getPassword(),
                updatePassword.getPassword());
    }

    @Test
    public void whenUserTriedGoInThenReturnFromDBLoginAndPassword() {
        User user1 = new User(8, "Serhet", "Gavrilov", "paketchibogfdgh", "12345");
        User user2 = new User(6, "Roman", "Alexsseyev", "talin22", "zxcvb");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        assertEquals(userRepository.findAllForTest(), List.of(user1, user2));
        Optional enter = userRepository.findLoginAndPassword(user1.getLogin(), user1.getPassword());
        assertEquals(user1, enter.get());
    }

}