package cars.storagerepository;

import cars.model.Advertisement;
import cars.model.BodyCar;
import cars.model.Engine;
import cars.model.Mark;
import cars.storagerepository.interfacerepository.AdsRepositoryInterface;
import cars.storagerepository.interfacerepository.DefaultMethod;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@ThreadSafe
public class AdsRepository implements AdsRepositoryInterface, DefaultMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private final SessionFactory sessionFactory;

    public AdsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Advertisement> findAll() {
        LOGGER.info("Начало поиск Advertisement");
        return tx(session ->
                session.createQuery("select distinct u from Advertisement u join fetch u.user")
                        .list(), sessionFactory);
    }

    @Override
    public Optional<Advertisement> addAds(Advertisement advertisement) {
        LOGGER.info("Начато добавления Advertisement");
        Optional<Advertisement> optionalAdvertisement = Optional.empty();
        Advertisement ads = this.tx(session -> {
            session.save(advertisement);
            return advertisement;
        }, sessionFactory);
        if (advertisement.getId() != 0) {
            LOGGER.info("Добавления Advertisement произведено");
            optionalAdvertisement = Optional.of(ads);
        }
        return optionalAdvertisement;
    }

    @Override
    public Optional<Advertisement> findByIdAds(int id) {
        LOGGER.info("Начат поиск Advertisement по Id ");
        return tx(session ->
                session.createQuery("from Advertisement a where a.id = :aId")
                        .setParameter("aId", id).uniqueResultOptional(), sessionFactory);
    }

    @Override
    public boolean updateAds(Advertisement advertisement) {
        LOGGER.info("Начато обновление Advertisement");
        return tx(session -> session
                .createQuery("update Advertisement as a "
                        + "set a.header =:aHead, a.description =:aDesc, "
                        + "a.photo =:aPhoto, a.mark.id =:aNewMark, "
                        + "a.engine.id =:aEngine, a.bodyCar.id =:aBodyCar "
                        + "where a.id = :aId")
                .setParameter("aHead", advertisement.getHeader())
                .setParameter("aDesc", advertisement.getDescription())
                .setParameter("aPhoto", advertisement.getPhoto())
                .setParameter("aNewMark", advertisement.getMark().getId())
                .setParameter("aEngine", advertisement.getEngine().getId())
                .setParameter("aBodyCar", advertisement.getBodyCar().getId())
                .setParameter("aId", advertisement.getId())
                .executeUpdate() > 0, sessionFactory);
    }

    @Override
    public boolean updateAdsStatus(Advertisement advertisement) {
        LOGGER.info("Начато обновление статуса Advertisement");
        return tx(session -> session
                .createQuery("update Advertisement a set a.isCell =:aCell")
                .setParameter("aCell", true)
                .executeUpdate() > 0, sessionFactory);
    }

    @Override
    public boolean deleteAds(Advertisement advertisement) {
        LOGGER.info("Начато удаление Advertisement");
        return tx(session -> session.createQuery("delete from Advertisement as a where a.id =:aId")
                .setParameter("aId", advertisement.getId())
                .executeUpdate() > 0, sessionFactory);
    }

    @Override
    public List<Advertisement> findAllAdsWhereStatusTrue() {
        LOGGER.info("Поиск всех Advertisement со статусом true");
        return tx(session ->
                session.createQuery("select distinct a from Advertisement "
                        + "a join fetch a.bodyCar where a.isCell= :aCell")
                .setParameter("aCell", true).list(), sessionFactory);
    }

    @Override
    public List<Mark> findAllMarkCar() {
        LOGGER.info("Поиск всех Mark");
        return tx(session ->
                session.createQuery("from Mark").list(), sessionFactory);
    }

    @Override
    public List<BodyCar> findALLBodyCar() {
        LOGGER.info("Поиск всех каркасов");
        return tx(session ->
                session.createQuery("from BodyCar").list(), sessionFactory);
    }

    @Override
    public List<Engine> findAllEngines() {
        LOGGER.info("Поиск всех двигателей");
        return tx(session ->
                session.createQuery("from Engine").list(), sessionFactory);
    }
}
