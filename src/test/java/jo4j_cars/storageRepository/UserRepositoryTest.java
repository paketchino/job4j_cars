package jo4j_cars.storageRepository;

import jo4j_cars.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {


    public SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void whenAddUserThenUser() {
        User user = new User(2, "Ivan", "Ivanov", "Ivan", "12345");
        UserRepository userRepository = new UserRepository(sf());
//        userRepository.addUser(user);
        assertEquals(user, userRepository.findAllForTest().get(1));
    }

    @Test
    public void whenAddAndFindUserThenReturnUser() {
        User user = new User(3, "Sanya", "Gavrilov", "HITJAD", "12345");
        UserRepository userRepository = new UserRepository(sf());
//        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById, user);
    }

    @Test
    public void whenUpdateUserLoginThenReturnUpdatesLoginsUser() {
        User user = new User(4, "Serhet", "Gavrilov", "paketchibo", "12345");
        User updateLogin = new User(4,  "Serhet", "Gavrilov", "fdgdfgcv", "12345");
        UserRepository userRepository = new UserRepository(sf());
//        userRepository.addUser(user);
        User findById = userRepository.findByIdUser(user.getId()).get();
        assertEquals(findById, user);
//        assertTrue(userRepository.updateLogin(updateLogin));

        assertEquals(updateLogin.getLogin(), userRepository.findAllForTest().get(3).getLogin());
    }
//
//    @Test
//    public void whenUpdateUserFirstNameAndSecondNameThenReturnUpdatesDateFNAndSN() {
//        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
//        User updateName = new User(1,  "Stephan", "Zaharev", "tatackacha", "12345");
//        UserRepository userRepository = new UserRepository(sf());
//        userRepository.addUser(user);
//        User findById = userRepository.findByIdUser(user.getId()).get();
//
//        assertEquals(findById.getNameOne(), user.getNameOne());
//        assertEquals(findById.getNameTwo(), user.getNameTwo());
//        assertTrue(userRepository.updateUserFirstNameAndSecondName(updateName));
//        assertEquals(userRepository.findAllForTest().get(0).getNameOne(), updateName.getNameOne());
//        assertEquals(userRepository.findAllForTest().get(0).getNameTwo(), updateName.getNameTwo());
//    }
//
//    @Test
//    public void whenUpdateUserPasswordThenReturnUpdatesPassword() {
//        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
//        User updatePassword = new User(1,  "Stephan", "Zaharev", "tatackacha", "qwert");
//        UserRepository userRepository = new UserRepository(sf());
//        userRepository.addUser(user);
//        User findById = userRepository.findByIdUser(user.getId()).get();
//
//        assertEquals(findById.getPassword(), user.getPassword());
//        assertTrue(userRepository.updatePasswordUser(updatePassword));
//        assertEquals(userRepository.findAllForTest().get(0).getPassword(), updatePassword.getPassword());
//    }
//
//    @Test
//    public void whenUserTriedGoInThenReturnFromDBLoginAndPassword() {
//        User user1 = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
//        User user2 = new User(2, "Roman", "Alexsseyev", "talin22", "zxcvb");
//        UserRepository userRepository = new UserRepository(sf());
//        userRepository.addUser(user1);
//        userRepository.addUser(user2);
//
//        assertEquals(userRepository.findAllForTest(), List.of(user1, user2));
//        Optional enter = userRepository.findLoginAndPassword(user1.getLogin(), user1.getPassword());
//        assertEquals(enter.get(), user1);
//    }

}