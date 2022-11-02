package ru.joj4j.cars.storagerepository.interfacerepository;

import ru.joj4j.cars.model.BodyCar;
import ru.joj4j.cars.model.Car;
import ru.joj4j.cars.model.Engine;
import ru.joj4j.cars.model.Mark;

import java.util.List;
import java.util.Optional;

public interface CarRepositoryInterface {

    Optional<Car> addCar(Car car);

    List<Car> findAllCar();

    Optional<Car> findById(int id);

    Optional<Engine> addEngine(Engine engine);

    Optional<Mark> addMark(Mark mark);

    Optional<BodyCar> addBodyCar(BodyCar bodyCar);

    List<Mark> findAllMark();

    List<BodyCar> findAllBodyType();

    List<Engine> findAllEngineForTest();

    Optional<BodyCar> findBodyCarById(int id);

    Optional<Engine> findEngineById(int id);

    Optional<Mark> findMarkById(int id);

    boolean updateCar(Car car);
}
