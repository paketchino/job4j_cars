package jo4j_cars.service;

import jo4j_cars.model.BodyCar;
import jo4j_cars.model.Car;
import jo4j_cars.model.Engine;
import jo4j_cars.model.Mark;
import jo4j_cars.service.interfaceservice.CarServiceInterface;
import jo4j_cars.storageRepository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> addCar(Car car) {
        return carRepository.addCar(car);
    }

    @Override
    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Engine> addEngine(Engine engine) {
        return carRepository.addEngine(engine);
    }

    @Override
    public Optional<Mark> addMark(Mark mark) {
        return carRepository.addMark(mark);
    }

    @Override
    public Optional<BodyCar> addBodyCar(BodyCar bodyCar) {
        return carRepository.addBodyCar(bodyCar);
    }

    @Override
    public List<Mark> findAllMark() {
        return carRepository.findAllMark();
    }

    @Override
    public List<BodyCar> findAllBodyType() {
        return carRepository.findAllBodyType();
    }

    @Override
    public List<BodyCar> findBodyCarById(int id) {
        return carRepository.findBodyCarById(id);
    }

    @Override
    public List<Engine> findEngineById(int id) {
        return carRepository.findEngineById(id);
    }

    @Override
    public List<Mark> findMarkById(int id) {
        return carRepository.findMarkById(id);
    }

}
