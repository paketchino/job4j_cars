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
import java.util.List;
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
        BodyCar bodyCar = new BodyCar(bodyCarString);
        String stringMark = "Очень спортивная марка" + System.nanoTime();
        Mark mark = new Mark(stringMark);
        String engineString = "Мощь измеримая в лошадях, в живых " + System.nanoTime();
        Engine engine = new Engine(engineString);
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. "
                + "Ну почти, отвечаю, мамой клянусь";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        Advertisement advertisement =
                new Advertisement(header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);
        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());
        userRepository.addUser(user);
        Assert.assertEquals(userRepository.findByIdUser(user.getId()).get(), user);

        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);

        Assert.assertEquals(carRepository.findMarkById(mark.getId()), List.of(mark));
        Assert.assertEquals(carRepository.findEngineById(engine.getId()), List.of(engine));
        Assert.assertEquals(carRepository.findBodyCarById(bodyCar.getId()), List.of(bodyCar));

        adsRepository.addAds(advertisement);
        Assert.assertEquals(adsRepository.
                findByIdAds(advertisement.getId()).get(), advertisement);
    }

    @Test
    public void whenUpdateAdsThenReturnUpdatesAds() {
        String userString = "paketchibo" + System.nanoTime();
        User user = new User("Serhet", "Gavrilov", userString, "12345");
        String bodyCarString = "Джип Огромный" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(bodyCarString);
        String updBodyCarString = "SEDAN" + System.nanoTime();
        BodyCar updateBodyCar = new BodyCar(updBodyCarString);
        String stringMark = "X5 " + System.nanoTime();
        Mark mark = new Mark(stringMark);
        String updateStringMark = "X35 " + System.nanoTime();
        Mark updateMark = new Mark(updateStringMark);
        String engineString = "Отвечаю быстрая " + System.nanoTime();
        Engine engine = new Engine(engineString);
        String updateEngineString = "Реактивный" + System.nanoTime();
        Engine updateEngine = new Engine(updateEngineString);

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

        Assert.assertEquals(carRepository.findBodyCarById(bodyCar.getId()), List.of(bodyCar));
        Assert.assertEquals(carRepository.findMarkById(mark.getId()), List.of(mark));
        Assert.assertEquals(carRepository.findEngineById(engine.getId()), List.of(engine));

        carRepository.addMark(updateMark);
        carRepository.addBodyCar(updateBodyCar);
        carRepository.addEngine(updateEngine);

        Assert.assertEquals(carRepository
                .findBodyCarById(updateBodyCar.getId()), List.of(updateBodyCar));
        Assert.assertEquals(carRepository
                .findMarkById(updateMark.getId()), List.of(updateMark));
        Assert.assertEquals(carRepository
                .findEngineById(updateEngine.getId()), List.of(updateEngine));

        userRepository.addUser(user);

        Assert.assertEquals(userRepository.findByIdUser(user.getId()).get(), user);

        Advertisement ads =
                new Advertisement(header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);
        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findByIdAds(ads.getId()).get(), ads);

        Advertisement updateAds =
                new Advertisement(ads.getId(), updateHeader, updateDesc, isCeil, photo,
                                LocalDateTime.now().withNano(0),
                                user, updateBodyCar, updateMark, updateEngine);

        adsRepository.updateAds(updateAds);
        Assert.assertEquals(adsRepository
                .findByIdAds(updateAds.getId()).get(), updateAds);
        Assert.assertEquals(adsRepository
                .findByIdAds(updateAds.getId()).get().getEngine(), updateAds.getEngine());
        Assert.assertEquals(adsRepository
                .findByIdAds(updateAds.getId()).get().getBodyCar(), updateAds.getBodyCar());
        Assert.assertEquals(adsRepository
                .findByIdAds(updateAds.getId()).get().getMark(), updateAds.getMark());
    }

    @Test
    public void updateAdsStatus() {
        String userString = "paketchibo" + System.nanoTime();
        User user = new User(1, "Serhet", "Gavrilov", userString, "12345");
        String bodyCarString = "Джип Огромный" + System.nanoTime();
        BodyCar bodyCar = new BodyCar(bodyCarString);
        String stringMark = "Очень спортивная марка " + System.nanoTime();
        Mark mark = new Mark(stringMark);
        String engineString = "Мощь измеримая в лошадях, в живых" + System.nanoTime();
        Engine engine = new Engine(engineString);
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. "
                + "Ну почти, отвечаю, мамой клянусь";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        Advertisement ads =
                new Advertisement(header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);
        Advertisement adsUpdateStatus =
                new Advertisement(ads.getId(), header, desc, true, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);

        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());

        userRepository.addUser(user);
        Assert.assertEquals(userRepository.findByIdUser(user.getId()).get(), user);

        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);

        Assert.assertEquals(carRepository.findMarkById(mark.getId()), List.of(mark));
        Assert.assertEquals(carRepository.findEngineById(engine.getId()), List.of(engine));
        Assert.assertEquals(carRepository.findBodyCarById(bodyCar.getId()), List.of(bodyCar));

        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findByIdAds(ads.getId()).get(), ads);

        Assert.assertTrue(adsRepository.updateAdsStatus(adsUpdateStatus));
        Assert.assertEquals(adsRepository
                .findAllAdsWhereStatusTrue().get(0).isCell(), adsUpdateStatus.isCell());
    }

    @Test
    public void deleteAds() {
        String userString = "paketchibo " + System.nanoTime();
        User user = new User("Serhet", "Gavrilov", userString, "12345");
        String bodyCarString = "Джип Огромный " + System.nanoTime();
        BodyCar bodyCar = new BodyCar(bodyCarString);
        String stringMark = "Очень спортивная марка " + System.nanoTime();
        Mark mark = new Mark(stringMark);
        String engineString = "Мощь измеримая в лошадях, в живых " + System.nanoTime();
        Engine engine = new Engine(engineString);
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. "
                + "Ну почти, отвечаю, мамой клянусь";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        Advertisement ads =
                new Advertisement(header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);
        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());

        userRepository.addUser(user);
        Assert.assertEquals(userRepository.findByIdUser(user.getId()).get(), user);

        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);

        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findByIdAds(ads.getId()).get(), ads);

        Assert.assertTrue(adsRepository.deleteAds(ads.getId()));
        Assert.assertEquals(adsRepository.findByIdAds(ads.getId()), Optional.empty());
    }
}