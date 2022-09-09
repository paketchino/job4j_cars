package cars.storagerepository;

import cars.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

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
        String login = "ivanov233" + System.nanoTime();
        User user = new User("Ivan", "Ivanov", login, "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        assertEquals(user, userRepository.findByIdUser(user.getId()).get());
    }

    @Test
    public void whenAddAndFindUserThenReturnUser() {
        String login = "HITJAD" + System.nanoTime();
        User user = new User("Sanya", "Gavrilov", login, "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById, user);
    }

    @Test
    public void whenUpdateUserLoginThenReturnUpdatesLoginsUser() {
        UserRepository userRepository = new UserRepository(sf());
        String login = "paketchibo" + System.nanoTime();
        User user = new User(30, "Serhet", "Gavrilov", login, "12345");
        String updateLog = "fdgdddfgcv" + System.nanoTime();
        User updateLogin = new User(user.getId(), "Serhet", "Gavrilov", updateLog, "12345");
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById, user);
        assertTrue(userRepository.updateLogin(updateLogin));
        assertEquals(updateLogin.getLogin(),
                userRepository.findByIdUser(updateLogin.getId()).get().getLogin());
    }

    @Test
    public void whenUpdateUserFirstNameAndSecondNameThenReturnUpdatesDateFNAndSN() {
        UserRepository userRepository = new UserRepository(sf());
        String login = "fdgdfgcv" + System.nanoTime();
        User user = new User("Alesey", "Gavrichenko", login, "12345");
        userRepository.addUser(user);
        String updateLogin = login;
        User updateName = new User(user.getId(), "Stephan", "Zaharev", updateLogin, "12345");
        Optional<User> findById = userRepository.findByIdUser(user.getId());
        assertEquals(findById.get().getNameOne(), user.getNameOne());
        assertEquals(findById.get().getNameTwo(), user.getNameTwo());
        userRepository.updateUserFirstNameAndSecondName(updateName);
        assertEquals(userRepository.
                findByIdUser(updateName.getId()).get().getNameOne(), updateName.getNameOne());
        assertEquals(userRepository.
                findByIdUser(updateName.getId()).get().getNameTwo(), updateName.getNameTwo());
    }

    @Test
    public void whenUpdateUserPasswordThenReturnUpdatesPassword() {
        UserRepository userRepository = new UserRepository(sf());
        String login = "balabeiv_point" + System.nanoTime();
        User user = new User("Stephan", "Zaharev", login, "12345");
        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById.getPassword(), user.getPassword());
        User updatePassword = new User(user.getId(),  "Stephan", "Zaharev", login, "qwert");
        assertTrue(userRepository.updatePasswordUser(updatePassword));
        assertEquals(userRepository.findByIdUser(updatePassword.getId()).get().getPassword(),
                updatePassword.getPassword());
    }

    @Test
    public void whenUserTriedGoInThenReturnFromDBLoginAndPassword() {
        String loginU1 = "paketchibogfdgh" + System.nanoTime();
        String loginU2 = "talin22" + System.nanoTime();
        User user1 = new User("Serhet", "Gavrilov", loginU1, "12345");
        User user2 = new User("Roman", "Alexsseyev", loginU2, "zxcvb");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        assertEquals(userRepository.findByIdUser(user1.getId()).get(), user1);
        assertEquals(userRepository.findByIdUser(user2.getId()).get(), user2);
        Optional enterU1 = userRepository
                .findLoginAndPassword(user1.getLogin(), user1.getPassword());
        Optional enterU2 = userRepository
                .findLoginAndPassword(user2.getLogin(), user2.getPassword());
        assertEquals(user1, enterU1.get());
        assertEquals(user2, enterU2.get());
    }

}