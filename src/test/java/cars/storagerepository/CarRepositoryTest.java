package cars.storagerepository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;
import ru.joj4j.cars.model.*;
import ru.joj4j.cars.storagerepository.CarRepository;
import ru.joj4j.cars.storagerepository.UserRepository;

public class CarRepositoryTest {

    public SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void whenAddEngineThenReturnEngineName() {
        CarRepository carRepository = new CarRepository(sf());
        String stringEngine = "2.5L" + System.nanoTime();
        Engine engine = new Engine(1, stringEngine);
        Assert.assertEquals(carRepository.addEngine(engine).get(), engine);
    }

    @Test
    public void whenAddMarkThenMarkName() {
        String stringMark = "X4" + System.nanoTime();
        Mark mark = new Mark(1, stringMark);
        CarRepository carRepository = new CarRepository(sf());
        carRepository.addMark(mark);
        Assert.assertEquals(mark, carRepository.findMarkById(mark.getId()).get());
    }

    @Test
    public void whenAddCarcaseThenCarcaseReturn() {
        String hatchBack = "ХэтчБэк" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(1, hatchBack);
        CarRepository carRepository = new CarRepository(sf());
        carRepository.addBodyCar(bodyCar);
        Assert.assertEquals(bodyCar, carRepository.findBodyCarById(bodyCar.getId()).get());
    }

    @Test
    public void whenAddCarAddTypeBodyMarkEngineBodyCarThenReturnFullCar() {
        String uniqueLogin = "LOGIN" + System.nanoTime();
        User user = new User("name", "USER", uniqueLogin, "PASSWORD");
        String hatchBack = "ХэтчБэк" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(1, hatchBack);
        String engineSt = "Мощный" + System.nanoTime();
        Engine engine = new Engine(1, engineSt);
        String markX5 = "X5" + System.nanoTime();
        Mark mark = new Mark(1, markX5);
        String carString = "BMW" + System.nanoTime();
        Car car = new Car(1, mark, engine, bodyCar);
        UserRepository userRepository = new UserRepository(sf());
        userRepository.addUser(user);
        CarRepository carRepository = new CarRepository(sf());
        carRepository.addEngine(engine);
        Assert.assertEquals(carRepository.addBodyCar(bodyCar),
                carRepository.findBodyCarById(bodyCar.getId()));
        carRepository.addMark(mark);
        carRepository.addCar(car);

        Assert.assertEquals(carRepository.findById(car.getId()).get(), car);
    }

}