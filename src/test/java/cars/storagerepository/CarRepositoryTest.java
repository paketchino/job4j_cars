package cars.storagerepository;

import cars.model.BodyCar;
import cars.model.Car;
import cars.model.Engine;
import cars.model.Mark;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
        Engine engine = new Engine(stringEngine);
        Assert.assertEquals(carRepository.addEngine(engine).get(), engine);
    }

    @Test
    public void whenAddMarkThenMarkName() {
        String stringMark = "X4" + System.nanoTime();
        Mark mark = new Mark(stringMark);
        CarRepository carRepository = new CarRepository(sf());
        carRepository.addMark(mark);
        Assert.assertEquals(List.of(mark), carRepository.findMarkById(mark.getId()));
    }

    @Test
    public void whenAddCarcaseThenCarcaseReturn() {
        String hatchBack = "ХэтчБэк" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(hatchBack);
        CarRepository carRepository = new CarRepository(sf());
        carRepository.addBodyCar(bodyCar);
        Assert.assertEquals(List.of(bodyCar), carRepository.findBodyCarById(bodyCar.getId()));
    }

    @Test
    public void whenAddCarAddTypeBodyMarkEngineBodyCarThenReturnFullCar() {
        String hatchBack = "ХэтчБэк" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(hatchBack);
        String engineSt = "Мощный" + System.nanoTime();
        Engine engine = new Engine(engineSt);
        String markX5 = "X5" + System.nanoTime();
        Mark mark = new Mark(markX5);
        String carString = "BMW" + System.nanoTime();
        Car car = new Car(carString, mark, engine, bodyCar);

        CarRepository carRepository = new CarRepository(sf());
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);
        carRepository.addMark(mark);
        carRepository.addCar(car);

        Assert.assertEquals(carRepository.findById(car.getId()).get(), car);
    }

}