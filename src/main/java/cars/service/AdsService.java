package cars.service;

import cars.model.Advertisement;
import cars.model.BodyCar;
import cars.model.Engine;
import cars.model.Mark;
import cars.service.interfaceservice.AdsServiceInterface;
import cars.storagerepository.AdsRepository;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
public class AdsService implements AdsServiceInterface {

    private final AdsRepository adsRepository;

    public AdsService(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

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
    public boolean deleteAds(Advertisement advertisement) {
        return adsRepository.deleteAds(advertisement);
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
