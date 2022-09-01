package jo4j_cars.storageRepository;

import jo4j_cars.model.User;
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


    public SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void whenAddUserThenUser() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        assertEquals(userRepository.findAllForTest().get(0), user);
    }

    @Test
    public void whenAddAndFindUserThenReturnUser() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById, user);
    }

    @Test
    public void whenUpdateUserLoginThenReturnUpdatesLoginsUser() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        User updateLogin = new User(1,  "Serhet", "Gavrilov", "tatackacha", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById, user);
        assertTrue(userRepository.updateLogin(updateLogin));
        assertEquals(userRepository.findAllForTest().get(0).getLogin(), updateLogin.getLogin());
    }

    @Test
    public void whenUpdateUserFirstNameAndSecondNameThenReturnUpdatesDateFNAndSN() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        User updateName = new User(1,  "Stephan", "Zaharev", "tatackacha", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();

        assertEquals(findById.getNameOne(), user.getNameOne());
        assertEquals(findById.getNameTwo(), user.getNameTwo());
        assertTrue(userRepository.updateUserFirstNameAndSecondName(updateName));
        assertEquals(userRepository.findAllForTest().get(0).getNameOne(), updateName.getNameOne());
        assertEquals(userRepository.findAllForTest().get(0).getNameTwo(), updateName.getNameTwo());
    }

    @Test
    public void whenUpdateUserPasswordThenReturnUpdatesPassword() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        User updatePassword = new User(1,  "Stephan", "Zaharev", "tatackacha", "qwert");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();

        assertEquals(findById.getPassword(), user.getPassword());
        assertTrue(userRepository.updatePasswordUser(updatePassword));
        assertEquals(userRepository.findAllForTest().get(0).getPassword(), updatePassword.getPassword());
    }

    @Test
    public void whenUserTriedGoInThenReturnFromDBLoginAndPassword() {
        User user1 = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        User user2 = new User(2, "Roman", "Alexsseyev", "talin22", "zxcvb");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        assertEquals(userRepository.findAllForTest(), List.of(user1, user2));
        Optional enter = userRepository.findLoginAndPassword(user1.getLogin(), user1.getPassword());
        assertEquals(enter.get(), user1);
    }

}