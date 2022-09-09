package cars.storagerepository;

import cars.model.BodyCar;
import cars.model.Car;
import cars.model.Engine;
import cars.model.Mark;
import cars.storagerepository.interfacerepository.CarRepositoryInterface;
import cars.storagerepository.interfacerepository.DefaultMethod;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository implements CarRepositoryInterface, DefaultMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private final SessionFactory sessionFactory;

    public CarRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Car> addCar(Car car) {
        LOGGER.info("Начало добавления машины");
        Optional<Car> optionalCar = Optional.empty();
        Car c = tx(session -> {
            session.save(car);
            return car;
        }, sessionFactory);
        if (car.getId() != 0) {
            LOGGER.info("Добавление машины произведено");
            optionalCar = Optional.of(c);
        }
        return optionalCar;
    }

    @Override
    public List<Car> findAllCarForTest() {
        LOGGER.info("Список всех машин");
        return tx(session -> session
                .createQuery("from Car").list(), sessionFactory);
    }

    @Override
    public Optional<Car> findById(int id) {
        LOGGER.info("Поиск машины по Id");
        return tx(session -> session.createQuery("from Car c where c.id= :cId")
                .setParameter("cId", id).uniqueResultOptional(), sessionFactory);
    }

    @Override
    public Optional<Engine> addEngine(Engine engine) {
        LOGGER.info("Начало добавления engine");
        Optional<Engine> result = Optional.empty();
        Engine e = tx(session -> {
            session.save(engine);
            return engine;
        }, sessionFactory);
        if (engine.getId() != 0) {
            LOGGER.info("Добавления engine успешно произведено");
            result = Optional.of(e);
        }
        return result;
    }

    @Override
    public Optional<Mark> addMark(Mark mark) {
        LOGGER.info("Начало добавления mark");
        Optional<Mark> markOptional = Optional.empty();
        Mark m = tx(session -> {
            session.save(mark);
            return mark;
        }, sessionFactory);
        if (mark.getId() != 0) {
            LOGGER.info("Добавления mark успешно произведено");
            markOptional = Optional.of(m);
        }
        return markOptional;
    }

    @Override
    public Optional<BodyCar> addBodyCar(BodyCar bodyCar) {
        LOGGER.info("Начало добавления типа кузова");
        Optional<BodyCar> optionalBodyCar = Optional.empty();
        BodyCar bc = tx(session -> {
            session.save(bodyCar);
            return bodyCar;
        }, sessionFactory);
        if (bodyCar.getId() != 0) {
            LOGGER.info("Добавления carcase успешно произведено");
            optionalBodyCar = Optional.of(bc);
        }
        return optionalBodyCar;
    }

    @Override
    public List<Mark> findAllMark() {
        LOGGER.info("Начат поиск всех марок");
        return tx(session -> session.createQuery("from Mark").list(), sessionFactory);
    }

    @Override
    public List<BodyCar> findAllBodyType() {
        LOGGER.info("Начат поиск всех каркасов");
        return tx(session -> session.createQuery("from BodyCar").list(), sessionFactory);
    }

    @Override
    public List<Engine> findAllEngineForTest() {
        return tx(session -> session.createQuery("from Engine").list(), sessionFactory);
    }

    @Override
    public List<Mark> findMarkById(int id) {
        LOGGER.info("Начат поиск mark по id");
        return tx(session -> session.createQuery("from Mark m where m.id =: mId")
                .setParameter("mId", id)
                .list(), sessionFactory);
    }

    @Override
    public List<Engine> findEngineById(int id) {
        LOGGER.info("Начато поиск engine id");
        return tx(session -> session.createQuery("from Engine e where e.id =: eId")
                .setParameter("eId", id)
                .list(), sessionFactory);
    }

    @Override
    public List<BodyCar> findBodyCarById(int id) {
        LOGGER.info("Начат поиск каркаса по id");
        return tx(session -> session.createQuery("from BodyCar bc where bc.id =: bcId")
                .setParameter("bcId", id)
                .list(), sessionFactory);
    }

}
