package jo4j_cars.storageRepository;

import jo4j_cars.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {

    public SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void addUser() {
        User user = new User("Serhet", "Gavrilov", "paketchibo", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        assertEquals(user, userRepository.findAllForTest().get(0));
    }

    @Test
    public void find() {
        User user = new User("Serhet", "Gavrilov", "paketchibo", "12345");
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        Optional findById = userRepository.findByIdUser(user.getId());
        assertEquals(findById.get(), user);
    }

}