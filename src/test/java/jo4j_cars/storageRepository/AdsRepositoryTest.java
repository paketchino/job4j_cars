package jo4j_cars.storageRepository;

import jo4j_cars.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

    public class AdsRepositoryTest {

    public SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void whenAddAdsWithUserMarkEngineBodyCarThenReturnFilledUser() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        BodyCar bodyCar = new BodyCar("Джип Огромный");
        Mark mark = new Mark("Очень спортивная марка");
        Engine engine = new Engine("Мощь измеримая в лошадях, в живых");
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. Ну почти, отвечаю, мамой клянусь";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        Advertisement advertisement1 =
                new Advertisement
                        (1, header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);

        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());

        userRepository.addUser(user);
        Assert.assertEquals(userRepository.findAllForTest().get(0), user);

        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);

        Assert.assertEquals(carRepository.findAllMark().get(0), mark);
        Assert.assertEquals(carRepository.findAllEngineForTest().get(0), engine);
        Assert.assertEquals(carRepository.findAllBodyType().get(0), bodyCar);

        adsRepository.addAds(advertisement1);
        Assert.assertEquals(adsRepository.findAll().get(0), advertisement1);
        Assert.assertEquals(adsRepository.findByIdAds(advertisement1.getId()).get(), advertisement1);

    }

    @Test
    public void whenUpdateAdsThenReturnUpdatesAds() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");

        BodyCar bodyCar = new BodyCar("Джип Огромный");
        BodyCar updateBodyCar = new BodyCar("SEDAN");

        Mark mark = new Mark("X5");
        Mark updateMark = new Mark("35");

        Engine engine = new Engine("Отвечаю быстрая");
        Engine updateEngine = new Engine("Реактивный");

        String header = "Машина на костылях, отвечаю едет";
        String updateHeader = "Машина BMW X5 в хорошем состоянии. Ни битая, не крашенная.";

        String desc = "Машина не бита не крашена, не была в угоне. Ну почти, отвечаю, мамой клянусь";
        String updateDesc = "Продаю машину потому, что купил новую машину. Эта стала не нужна. Все остальные вопросы в лс";

        boolean isCeil = false;
        byte[] photo = new byte[]{};

        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());

        carRepository.addMark(mark);
        carRepository.addBodyCar(bodyCar);
        carRepository.addEngine(engine);

        Assert.assertEquals(carRepository.findAllBodyType().get(0), bodyCar);
        Assert.assertEquals(carRepository.findAllMark().get(0), mark);
        Assert.assertEquals(carRepository.findAllEngineForTest().get(0), engine);

        carRepository.addMark(updateMark);
        carRepository.addBodyCar(updateBodyCar);
        carRepository.addEngine(updateEngine);

        Assert.assertEquals(carRepository.findAllBodyType().get(1), updateBodyCar);
        Assert.assertEquals(carRepository.findAllMark().get(1), updateMark);
        Assert.assertEquals(carRepository.findAllEngineForTest().get(1), updateEngine);

        userRepository.addUser(user);

        Assert.assertEquals(userRepository.findAllForTest().get(0), user);

        Advertisement ads =
                new Advertisement
                        (1, header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);


        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findAll().get(0), ads);

        Advertisement updateAds =
                new Advertisement
                        (1, updateHeader, updateDesc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, updateBodyCar, updateMark, updateEngine);

        adsRepository.updateAds(updateAds);
        Assert.assertEquals(adsRepository.findAll().get(0), updateAds);
    }

    @Test
    public void updateAdsStatus() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        BodyCar bodyCar = new BodyCar("Джип Огромный");
        Mark mark = new Mark("Очень спортивная марка");
        Engine engine = new Engine("Мощь измеримая в лошадях, в живых");
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. Ну почти, отвечаю, мамой клянусь";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        Advertisement ads =
                new Advertisement
                        (1, header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);
        Advertisement adsUpdateStatus =
                new Advertisement
                        (1, header, desc, true, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);

        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());

        userRepository.addUser(user);
        Assert.assertEquals(userRepository.findAllForTest().get(0), user);

        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);

        Assert.assertEquals(carRepository.findAllMark().get(0), mark);
        Assert.assertEquals(carRepository.findAllEngineForTest().get(0), engine);
        Assert.assertEquals(carRepository.findAllBodyType().get(0), bodyCar);

        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findAll().get(0), ads);

        Assert.assertTrue(adsRepository.updateAdsStatus(adsUpdateStatus));
        Assert.assertEquals(adsRepository.findAllAdsWhereStatusTrue().get(0), adsUpdateStatus);
    }

    @Test
    public void deleteAds() {
        User user = new User(1, "Serhet", "Gavrilov", "paketchibo", "12345");
        BodyCar bodyCar = new BodyCar("Джип Огромный");
        Mark mark = new Mark("Очень спортивная марка");
        Engine engine = new Engine("Мощь измеримая в лошадях, в живых");
        String header = "Машина на костылях, отвечаю едет";
        String desc = "Машина не бита не крашена, не была в угоне. Ну почти, отвечаю, мамой клянусь";
        boolean isCeil = false;
        byte[] photo = new byte[]{};
        Advertisement ads =
                new Advertisement
                        (1, header, desc, isCeil, photo,
                                LocalDateTime.now().withNano(0), user, bodyCar, mark, engine);
        CarRepository carRepository = new CarRepository(sf());
        UserRepository userRepository = new UserRepository(sf());
        AdsRepository adsRepository = new AdsRepository(sf());

        userRepository.addUser(user);
        Assert.assertEquals(userRepository.findAllForTest().get(0), user);

        carRepository.addMark(mark);
        carRepository.addEngine(engine);
        carRepository.addBodyCar(bodyCar);

        adsRepository.addAds(ads);
        Assert.assertEquals(adsRepository.findAll().get(0), ads);

        Assert.assertTrue(adsRepository.deleteAds(ads));
        Assert.assertTrue(adsRepository.findAll().isEmpty());
    }
}