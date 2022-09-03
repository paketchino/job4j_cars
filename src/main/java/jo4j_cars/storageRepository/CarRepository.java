package jo4j_cars.storageRepository;

import jo4j_cars.model.BodyCar;
import jo4j_cars.model.Car;
import jo4j_cars.model.Engine;
import jo4j_cars.model.Mark;
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
    public List<Car> findAllCarForTest() {
        return tx(session -> session
                .createQuery("from Car").list(), sessionFactory);
    }

    @Override
    public Optional<Car> findById(int id) {
        return tx(session -> session.createQuery("from Car c where c.id= :cId")
                .setParameter("cId", id).uniqueResultOptional(), sessionFactory);
    }

    @Override
    public Optional<Engine> addEngine(Engine engine) {
        Optional<Engine> result = Optional.empty();
        Engine e = tx(session -> {
            session.save(engine);
            return engine;
        }, sessionFactory);
        if (engine.getId() != 0) {
            result = Optional.of(e);
        }
        return result;
    }

    @Override
    public Optional<Mark> addMark(Mark mark) {
        Optional<Mark> markOptional = Optional.empty();
        Mark m = tx(session -> {
            session.save(mark);
            return mark;
        }, sessionFactory);
        if (mark.getId() != 0) {
            markOptional = Optional.of(m);
        }
        return markOptional;
    }

    @Override
    public Optional<BodyCar> addBodyCar(BodyCar bodyCar) {
        Optional<BodyCar> optionalBodyCar = Optional.empty();
        BodyCar bc = tx(session -> {
            session.save(bodyCar);
            return bodyCar;
        }, sessionFactory);
        if (bodyCar.getId() != 0) {
            optionalBodyCar = Optional.of(bc);
        }
        return optionalBodyCar;
    }

    @Override
    public List<Mark> findAllMark() {
        return tx(session -> session.createQuery("from Mark").list(), sessionFactory);
    }

    @Override
    public List<BodyCar> findAllBodyType() {
        return tx(session -> session.createQuery("from BodyCar").list(), sessionFactory);
    }

    @Override
    public List<Engine> findAllEngineForTest() {
        return tx(session -> session.createQuery("from Engine").list(), sessionFactory);
    }

    @Override
    public List<Mark> findMarkById(int id) {
        return tx(session -> session.createQuery("from Mark m where m.id =: mId")
                .setParameter("mId", id)
                .list(), sessionFactory);
    }

    @Override
    public List<Engine> findEngineById(int id) {
        return tx(session -> session.createQuery("from Engine e where e.id =: eId")
                .setParameter("eId", id)
                .list(), sessionFactory);
    }

    @Override
    public List<BodyCar> findBodyCarById(int id) {
        return tx(session -> session.createQuery("from BodyCar bc where bc.id =: bcId")
                .setParameter("bcId", id)
                .list(), sessionFactory);
    }

}