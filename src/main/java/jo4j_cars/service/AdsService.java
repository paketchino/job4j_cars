package jo4j_cars.service;

import jo4j_cars.model.Advertisement;
import jo4j_cars.model.BodyCar;
import jo4j_cars.model.Mark;
import jo4j_cars.storageRepository.AdsRepository;
import jo4j_cars.storageRepository.interfacerepository.AdsRepositoryInterface;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
public class AdsService implements AdsRepositoryInterface {

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
    public Optional<Advertisement> updateAds(Advertisement advertisement) {
        return adsRepository.updateAds(advertisement);
    }

    @Override
    public Optional<Advertisement> updateAdsStatus(Advertisement advertisement) {
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
        return null;
    }

    @Override
    public List<BodyCar> findALLBodyCar() {
        return null;
    }
}
