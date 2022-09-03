package cars.service.interfaceservice;

import cars.model.Advertisement;
import cars.model.BodyCar;
import cars.model.Engine;
import cars.model.Mark;

import java.util.List;
import java.util.Optional;

public interface AdsServiceInterface {

    List<Advertisement> findAll();

    Optional<Advertisement> addAds(Advertisement advertisement);

    Optional<Advertisement> findByIdAds(int id);

    boolean updateAds(Advertisement advertisement);

    boolean updateAdsStatus(Advertisement advertisement);

    boolean deleteAds(Advertisement advertisement);

    List<Advertisement> findAllAdsWhereStatusTrue();

    List<Mark> findAllMarkCar();

    List<BodyCar> findALLBodyCar();

    List<Engine> findAllEngine();
}
