package jo4j_cars.service.interfaceservice;

import jo4j_cars.model.BodyCar;
import jo4j_cars.model.Car;
import jo4j_cars.model.Engine;
import jo4j_cars.model.Mark;

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
