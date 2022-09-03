package cars.storagerepository.interfacerepository;

import cars.model.*;

import java.util.List;
import java.util.Optional;

public interface CarRepositoryInterface {

    Optional<Car> addCar(Car car);

    List<Car> findAllCarForTest();

    Optional<Car> findById(int id);

    Optional<Engine> addEngine(Engine engine);

    Optional<Mark> addMark(Mark mark);

    Optional<BodyCar> addBodyCar(BodyCar bodyCar);

    List<Mark> findAllMark();

    List<BodyCar> findAllBodyType();

    List<Engine> findAllEngineForTest();

    List<BodyCar> findBodyCarById(int id);

    List<Engine> findEngineById(int id);

    List<Mark> findMarkById(int id);
}
