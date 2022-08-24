package jo4j_cars.storageRepository;

import jo4j_cars.model.Car;
import jo4j_cars.storageRepository.interfacerepository.CarRepositoryInterface;
import jo4j_cars.storageRepository.interfacerepository.DefaultMethod;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository implements CarRepositoryInterface, DefaultMethod {

    private final SessionFactory sessionFactory;

    public CarRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Car> addCar(Car car) {
        Optional<Car> optionalCar = Optional.empty();
        Car c = tx(session -> {
            session.save(car);
            return car;
        }, sessionFactory);
        if (car.getId() != 0) {
            optionalCar = Optional.of(c);
        }
        return optionalCar;
    }

    @Override
    public List<Car> findAll() {
        return tx(session -> session
                .createQuery("select distinct c from Car c join fetch c.mark, c.bodyCar, c.engine").list(), sessionFactory);
    }

    @Override
    public Optional<Car> findById(int id) {
        return tx(session -> session.createQuery("from Car c where c.id= :cId").setParameter("cId", id).uniqueResultOptional(), sessionFactory);
    }


}
