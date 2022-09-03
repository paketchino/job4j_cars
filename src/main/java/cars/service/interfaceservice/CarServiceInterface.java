package cars.service.interfaceservice;

import cars.model.BodyCar;
import cars.model.Car;
import cars.model.Engine;
import cars.model.Mark;

import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {

    Optional<Car> addCar(Car car);

    Optional<Car> findById(int id);

    Optional<Engine> addEngine(Engine engine);

    Optional<Mark> addMark(Mark mark);

    Optional<BodyCar> addBodyCar(BodyCar bodyCar);

    List<Mark> findAllMark();

    List<BodyCar> findAllBodyType();

    List<BodyCar> findBodyCarById(int id);

    List<Engine> findEngineById(int id);

    List<Mark> findMarkById(int id);

}
