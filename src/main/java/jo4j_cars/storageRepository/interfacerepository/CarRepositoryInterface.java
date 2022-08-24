package jo4j_cars.storageRepository.interfacerepository;

import jo4j_cars.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepositoryInterface {

    Optional<Car> addCar(Car car);

    List<Car> findAll();

    Optional<Car> findById(int id);

}
