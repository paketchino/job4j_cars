package jo4j_cars.storageRepository;

import jo4j_cars.model.Advertisement;
import jo4j_cars.model.BodyCar;
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
                session.createQuery("from Advertisement")
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
                session.createQuery("select distinct a from Advertisement a where a.id = :aId")
                        .setParameter("aId", id).uniqueResultOptional(), sessionFactory);
    }

    @Override
    public Optional<Advertisement> updateAds(Advertisement advertisement) {
        return tx(session -> session
                .createQuery("update Advertisement as a set a.header =:aHead, a.description =:aDesc, " +
                        "a.photo =:aPhoto, a.mark.id =:aNewMark, a.engine.id =:aEngine, a.bodyCar.id =:aBodyCar where a.id = :aId")
                .setParameter("aHead", advertisement.getHeader())
                .setParameter("aDesc", advertisement.getDescription())
                .setParameter("aPhoto", advertisement.getPhoto())
                .setParameter("aNewMark", advertisement.getMark())
                .setParameter("aEngine", advertisement.getEngine())
                .setParameter("aBodyCar", advertisement.getBodyCar())
                .setParameter("aId", advertisement.getId())
                .uniqueResultOptional(), sessionFactory);
    }

    @Override
    public Optional<Advertisement> updateAdsStatus(Advertisement advertisement) {
        return tx(session -> session.createQuery("update Advertisement as a set a.isCell =:aCell").uniqueResultOptional(), sessionFactory);
    }

    @Override
    public boolean deleteAds(Advertisement advertisement) {
        return tx(session -> session.createQuery("delete from Advertisement as a where a.id =:aId").executeUpdate() > 0, sessionFactory);
    }

    @Override
    public List<Advertisement> findAllAdsWhereStatusTrue() {
        return tx(session -> session.createQuery("select distinct a from Advertisement " +
                "a join fetch a.engine, a.bodyCar, a.mark where a.isCell= :aCell").setParameter("aCell", true).list(), sessionFactory);
    }

    @Override
    public List<Mark> findAllMarkCar() {
        return tx(session -> session.createQuery("from Mark").list(), sessionFactory);
    }

    @Override
    public List<BodyCar> findALLBodyCar() {
        return tx(session -> session.createQuery("from BodyCar").list(), sessionFactory);
    }


}
