package jo4j_cars.service;

import jo4j_cars.model.Car;
import jo4j_cars.storageRepository.CarRepository;
import jo4j_cars.storageRepository.interfacerepository.CarRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarRepositoryInterface {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> addCar(Car car) {
        return carRepository.addCar(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }
}
