package cars.storagerepository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;
import ru.joj4j.cars.model.*;
import ru.joj4j.cars.storagerepository.AdsRepository;
import ru.joj4j.cars.storagerepository.CarRepository;
import ru.joj4j.cars.storagerepository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class AdsRepositoryTest {

    public SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void whenAddAdsWithUserMarkEngineBodyCarThenReturnFilledUser() {
        String userString = "paketchibo" + System.nanoTime();
        User user = new User("Serhet", "Gavrilov", userString, "12345");
        String bodyCarString = "Джип Огромный" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(2, bodyCarString);
        String stringMark = "Очень спортивная марка" + System.nanoTime();
        Mark mark = new Mark(2, stringMark);
        String engineString = "Мощь измеримая в лошадях, в живых " + System.nanoTime();
        Engine engine = new Engine(2, engineString);
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. "
                + "Ну почти, отвечаю, мамой клянусь";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        String name = "Car 1";
        Car car = new Car(1, mark, engine, bodyCar);
        Advertisement advertisement =
                new Advertisement(header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, car);
        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());
        userRepository.addUser(user);
        Assert.assertEquals(userRepository.findByIdUser(user.getId()).get(), user);

        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);
        carRepository.addCar(car);

        Assert.assertEquals(carRepository.findMarkById(mark.getId()).get(), mark);
        Assert.assertEquals(carRepository.findEngineById(engine.getId()).get(), engine);
        Assert.assertEquals(carRepository.findBodyCarById(bodyCar.getId()).get(), bodyCar);
        Assert.assertEquals(carRepository.findById(car.getId()).get(), car);
        Assert.assertEquals(carRepository.findById(car.getId()).get().getBodyCar(), bodyCar);

        adsRepository.addAds(advertisement);
        Assert.assertEquals(adsRepository.
                findByIdAds(advertisement.getId()).get(), advertisement);
    }

    @Test
    public void whenUpdateAdsThenReturnUpdatesAds() {
        String userString = "paketchibo" + System.nanoTime();
        User user = new User("Serhet", "Gavrilov", userString, "12345");
        String bodyCarString = "Джип Огромный" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(3, bodyCarString);
        String updBodyCarString = "SEDAN" + System.nanoTime();
        BodyCar updateBodyCar = new BodyCar(3, updBodyCarString);
        String stringMark = "X5 " + System.nanoTime();
        Mark mark = new Mark(3, stringMark);
        String updateStringMark = "X35 " + System.nanoTime();
        Mark updateMark = new Mark(4, updateStringMark);
        String engineString = "Отвечаю быстрая " + System.nanoTime();
        Engine engine = new Engine(3, engineString);
        String updateEngineString = "Реактивный" + System.nanoTime();
        Engine updateEngine = new Engine(5, updateEngineString);
        Car car = new Car(87, mark, engine, bodyCar);
        Car updateCar =
                new Car(car.getId(), updateMark, updateEngine, updateBodyCar);
        String header = "Машина на костылях, отвечаю едет";
        String updateHeader = "Машина BMW X5 в хорошем состоянии. Ни битая, не крашенная.";
        String desc = "Машина не бита не крашена, не была в угоне. "
                + "Ну почти, отвечаю, мамой клянусь";
        String updateDesc = "Продаю машину потому, что купил новую машину. "
                + "Эта стала не нужна. Все остальные вопросы в лс";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());

        carRepository.addMark(mark);
        carRepository.addBodyCar(bodyCar);
        carRepository.addEngine(engine);

        Assert.assertEquals(carRepository.findBodyCarById(bodyCar.getId()).get(), bodyCar);
        Assert.assertEquals(carRepository.findMarkById(mark.getId()).get(), mark);
        Assert.assertEquals(carRepository.findEngineById(engine.getId()).get(), engine);

        carRepository.addMark(updateMark);
        carRepository.addBodyCar(updateBodyCar);
        carRepository.addEngine(updateEngine);

        userRepository.addUser(user);

        carRepository.addCar(car);
        carRepository.addCar(updateCar);

        Assert.assertEquals(userRepository.findByIdUser(user.getId()).get(), user);
        Advertisement ads =
                new Advertisement(header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, car);
        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findByIdAds(ads.getId()).get(), ads);

        Advertisement updateAds =
                new Advertisement(ads.getId(), updateHeader, updateDesc, isCeil, photo,
                                LocalDateTime.now().withNano(0),
                                user, updateCar);

        adsRepository.updateAds(updateAds);
        Assert.assertEquals(adsRepository
                .findByIdAds(updateAds.getId()).get(), updateAds);
        Assert.assertEquals(adsRepository
                .findByIdAds(updateAds.getId()).get().getCar().getEngine(),
                updateAds.getCar().getEngine());
        Assert.assertEquals(adsRepository
                .findByIdAds(updateAds.getId()).get().getCar().getBodyCar(),
                updateAds.getCar().getBodyCar());
        Assert.assertEquals(adsRepository
                .findByIdAds(updateAds.getId()).get().getCar().getMark(),
                updateAds.getCar().getMark());
    }

    @Test
    public void updateAdsStatus() {
        String userString = "paketchibo" + System.nanoTime();
        User user = new User(1, "Serhet", "Gavrilov", userString, "12345");
        String bodyCarString = "Джип Огромный" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(10, bodyCarString);
        String stringMark = "Очень спортивная марка " + System.nanoTime();
        Mark mark = new Mark(10, stringMark);
        String engineString = "Мощь измеримая в лошадях, в живых" + System.nanoTime();
        Engine engine = new Engine(10, engineString);
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. "
                + "Ну почти, отвечаю, мамой клянусь";
        byte[] photo = new byte[]{};
        Car car = new Car(3, mark, engine, bodyCar);
        Car updateCar = new Car(car.getId(), car.getMark(), car.getEngine(),
                car.getBodyCar());
        Advertisement ads =
                new Advertisement(header, desc, false, photo,
                                LocalDateTime.now().withNano(0), user, car);
        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());

        userRepository.addUser(user);
        Assert.assertEquals(userRepository.findByIdUser(user.getId()).get(), user);

        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);
        carRepository.addCar(car);
        carRepository.addCar(updateCar);

        Assert.assertEquals(carRepository.findMarkById(mark.getId()).get(), mark);
        Assert.assertEquals(carRepository.findEngineById(engine.getId()).get(), engine);
        Assert.assertEquals(carRepository.findBodyCarById(bodyCar.getId()).get(), bodyCar);
        Assert.assertEquals(carRepository.findById(car.getId()).get(), car);

        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findByIdAds(ads.getId()).get(), ads);
        Advertisement adsUpdateStatus =
                new Advertisement(ads.getId(), header, desc, true, photo,
                        LocalDateTime.now().withNano(0), user, updateCar);

        Assert.assertTrue(adsRepository.updateAdsStatus(adsUpdateStatus));
        Assert.assertTrue(adsRepository
                .findAllAdsWhereStatusTrue().get(0).isCell());
    }

    @Test
    public void deleteAds() {
        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());
        String userString = "paketchibo " + System.nanoTime();
        User user = new User("Serhet", "Gavrilov", userString, "12345");
        String bodyCarString = "Джип Огромный " + System.nanoTime();
        BodyCar bodyCar = new BodyCar(1, bodyCarString);
        String stringMark = "Очень спортивная марка " + System.nanoTime();
        Mark mark = new Mark(1, stringMark);
        String engineString = "Мощь измеримая в лошадях, в живых " + System.nanoTime();
        Engine engine = new Engine(1, engineString);
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. "
                + "Ну почти, отвечаю, мамой клянусь";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);
        Car car = new Car(67,
                carRepository.findMarkById(mark.getId()).get(),
                carRepository.findEngineById(engine.getId()).get(),
                carRepository.findBodyCarById(bodyCar.getId()).get());
        carRepository.addCar(car);
        userRepository.addUser(user);
        Advertisement ads =
                new Advertisement(header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, car);

        Assert.assertEquals(userRepository.findByIdUser(user.getId()).get(), user);

        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findByIdAds(ads.getId()).get(), ads);

        Assert.assertTrue(adsRepository.deleteAds(ads.getId()));
        Assert.assertEquals(adsRepository.findByIdAds(ads.getId()), Optional.empty());
    }
}