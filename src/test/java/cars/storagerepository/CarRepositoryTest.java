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

public class CarRepositoryTest {

    public SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void whenAddEngineThenReturnEngineName() {
        Engine engine = new Engine("2.5L");
        CarRepository carRepository = new CarRepository(sf());
        Assert.assertEquals(carRepository.addEngine(engine).get(), engine);
    }

    @Test
    public void whenAddMarkThenMarkName() {
        Mark mark = new Mark("X4");
        CarRepository carRepository = new CarRepository(sf());
        carRepository.addMark(mark);
        Assert.assertEquals(mark, carRepository.findAllMark().get(0));
    }

    @Test
    public void whenAddCarAddTypeBodyMarkEngineBodyCarThenReturnFullCar() {
        BodyCar bodyCar = new BodyCar("ХэтчБэк");
        Engine engine = new Engine("Мощный");
        Mark mark = new Mark("X5");
        Car car = new Car("BWM", mark, engine, bodyCar);

        CarRepository carRepository = new CarRepository(sf());
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);
        carRepository.addMark(mark);
        carRepository.addCar(car);

        Assert.assertEquals(carRepository.findAllCarForTest().get(0), car);
        Assert.assertEquals(carRepository.findById(car.getId()).get(), car);
    }

}