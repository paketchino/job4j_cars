package ru.joj4j.cars.service.interfaceservice;

import ru.joj4j.cars.model.Advertisement;
import ru.joj4j.cars.model.BodyCar;
import ru.joj4j.cars.model.Engine;
import ru.joj4j.cars.model.Mark;

import java.util.List;
import java.util.Optional;

public interface AdsServiceInterface {

    List<Advertisement> findAll();

    Optional<Advertisement> addAds(Advertisement advertisement);

    Optional<Advertisement> findByIdAds(int id);

    boolean updateAds(Advertisement advertisement);

    boolean updateAdsStatus(Advertisement advertisement);

    boolean deleteAds(int id);

    List<Advertisement> findAllAdsWhereStatusTrue();

    List<Mark> findAllMarkCar();

    List<BodyCar> findALLBodyCar();

    List<Engine> findAllEngine();
}
