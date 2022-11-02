package ru.joj4j.cars.service;

import lombok.AllArgsConstructor;
import ru.joj4j.cars.model.Advertisement;
import ru.joj4j.cars.model.BodyCar;
import ru.joj4j.cars.model.Engine;
import ru.joj4j.cars.model.Mark;
import ru.joj4j.cars.service.interfaceservice.AdsServiceInterface;
import ru.joj4j.cars.storagerepository.AdsRepository;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@ThreadSafe
public class AdsService implements AdsServiceInterface {

    private final AdsRepository adsRepository;

    @Override
    public List<Advertisement> findAll() {
        return adsRepository.findAll();
    }

    @Override
    public Optional<Advertisement> addAds(Advertisement advertisement) {
        return adsRepository.addAds(advertisement);
    }

    @Override
    public Optional<Advertisement> findByIdAds(int id) {
        return adsRepository.findByIdAds(id);
    }

    @Override
    public boolean updateAds(Advertisement advertisement) {
        return adsRepository.updateAds(advertisement);
    }

    @Override
    public boolean updateAdsStatus(Advertisement advertisement) {
        return adsRepository.updateAdsStatus(advertisement);
    }

    @Override
    public boolean deleteAds(int id) {
        return adsRepository.deleteAds(id);
    }

    @Override
    public List<Advertisement> findAllAdsWhereStatusTrue() {
        return null;
    }

    @Override
    public List<Mark> findAllMarkCar() {
        return adsRepository.findAllMarkCar();
    }

    @Override
    public List<BodyCar> findALLBodyCar() {
        return adsRepository.findALLBodyCar();
    }

    @Override
    public List<Engine> findAllEngine() {
        return adsRepository.findAllEngines();
    }
}
