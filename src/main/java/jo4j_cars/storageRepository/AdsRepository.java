package jo4j_cars.storageRepository;

import jo4j_cars.model.Advertisement;
import jo4j_cars.model.BodyCar;
import jo4j_cars.model.Engine;
import jo4j_cars.model.Mark;
import jo4j_cars.storageRepository.interfacerepository.AdsRepositoryInterface;
import jo4j_cars.storageRepository.interfacerepository.DefaultMethod;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ThreadSafe
public class AdsRepository implements AdsRepositoryInterface, DefaultMethod {

    private final SessionFactory sessionFactory;

    public AdsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Advertisement> findAll() {
        return tx(session ->
                session.createQuery("select distinct u from Advertisement u join fetch u.user")
                        .list(), sessionFactory);
    }

    @Override
    public Optional<Advertisement> addAds(Advertisement advertisement) {
        Optional<Advertisement> optionalAdvertisement = Optional.empty();
        Advertisement ads = this.tx(session -> {
            session.save(advertisement);
            return advertisement;
        }, sessionFactory);
        if (advertisement.getId() != 0) {
            optionalAdvertisement = Optional.of(ads);
        }
        return optionalAdvertisement;
    }

    @Override
    public Optional<Advertisement> findByIdAds(int id) {
        return tx(session ->
                session.createQuery("from Advertisement a where a.id = :aId")
                        .setParameter("aId", id).uniqueResultOptional(), sessionFactory);
    }

    @Override
    public boolean updateAds(Advertisement advertisement) {

        return tx(session -> session
                .createQuery("update Advertisement as a set a.header =:aHead, a.description =:aDesc, " +
                        "a.photo =:aPhoto, a.mark.id =:aNewMark, a.engine.id =:aEngine, a.bodyCar.id =:aBodyCar where a.id = :aId")
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
        return tx(session -> session.createQuery("update Advertisement a set a.isCell =:aCell")
                .setParameter("aCell", true)
                .executeUpdate() > 0, sessionFactory);
    }

    @Override
    public boolean deleteAds(Advertisement advertisement) {
        return tx(session -> session.createQuery("delete from Advertisement as a where a.id =:aId")
                .setParameter("aId", advertisement.getId())
                .executeUpdate() > 0, sessionFactory);
    }

    @Override
    public List<Advertisement> findAllAdsWhereStatusTrue() {
        return tx(session -> session.createQuery("select distinct a from Advertisement " +
                "a join fetch a.bodyCar where a.isCell= :aCell").setParameter("aCell", true).list(), sessionFactory);
    }

    @Override
    public List<Mark> findAllMarkCar() {
        return tx(session -> session.createQuery("from Mark").list(), sessionFactory);
    }

    @Override
    public List<BodyCar> findALLBodyCar() {
        return tx(session -> session.createQuery("from BodyCar").list(), sessionFactory);
    }

    @Override
    public List<Engine> findAllEngines() {
        return tx(session -> session.createQuery("from Engine").list(), sessionFactory);
    }


}
