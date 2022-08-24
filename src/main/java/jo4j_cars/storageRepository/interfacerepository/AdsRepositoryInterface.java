package jo4j_cars.storageRepository.interfacerepository;

import jo4j_cars.model.Advertisement;
import jo4j_cars.model.BodyCar;
import jo4j_cars.model.Mark;

import java.util.List;
import java.util.Optional;

public interface AdsRepositoryInterface {

    List<Advertisement> findAll();

    Optional<Advertisement> addAds(Advertisement advertisement);

    Optional<Advertisement> findByIdAds(int id);

    Optional<Advertisement> updateAds(Advertisement advertisement);

    Optional<Advertisement> updateAdsStatus(Advertisement advertisement);

    boolean deleteAds(Advertisement advertisement);

    List<Advertisement> findAllAdsWhereStatusTrue();

    List<Mark> findAllMarkCar();

    List<BodyCar> findALLBodyCar();
}
