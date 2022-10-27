package ru.joj4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.joj4j.cars.model.Advertisement;
import ru.joj4j.cars.model.Car;
import ru.joj4j.cars.model.User;
import ru.joj4j.cars.service.AdsService;
import ru.joj4j.cars.service.CarService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class AdsController {
    private final AdsService adsService;
    private final CarService carService;

    public AdsController(AdsService adsService, CarService carService) {
        this.adsService = adsService;
        this.carService = carService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("advertisements", adsService.findAll());
        FindUser.findUser(model, session);
        return "index";
    }

    @GetMapping("/addAds")
    public String addAds(Model model, HttpSession session,
                         @RequestParam (name = "fail", required = false) Boolean fail) {
        FindUser.findUser(model, session);
        model.addAttribute("fail", fail != null);
        model.addAttribute("bodyCars", adsService.findALLBodyCar());
        model.addAttribute("marks", adsService.findAllMarkCar());
        model.addAttribute("engines", adsService.findAllEngine());
        return "addAds";
    }

    @PostMapping("/createAds")
    public String createAds(HttpSession session,
                            @ModelAttribute Advertisement advertisement,
                            @ModelAttribute Car car,
                            @RequestParam (name = "engineId") int engineId,
                            @RequestParam (name = "markId") int markId,
                            @RequestParam (name = "bodyId") int bodyId,
                            @RequestParam (name = "carId") int carId,
                            @RequestParam("file") MultipartFile file) throws IOException {
        advertisement.setUser((User) session.getAttribute("user"));
        car.setBodyCar(carService.findBodyCarById(bodyId).get());
        car.setMark(carService.findMarkById(markId).get());
        car.setEngine(carService.findEngineById(engineId).get());
        carService.addCar(car);
        advertisement.setCar(carService.findCarById(carId).get());
        advertisement.setPhoto(file.getBytes());
        advertisement.setCreated(LocalDateTime.now().withNano(0));
        adsService.addAds(advertisement);
        return "redirect:/index";
    }

    @GetMapping("/photoAds/{advertisementId}")
    public ResponseEntity<Resource> download(
            @PathVariable("advertisementId") Integer advertisementId) {
        Optional<Advertisement> advertisement = adsService.findByIdAds(advertisementId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(advertisement.get().getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(advertisement.get().getPhoto()));
    }

    @GetMapping("/advertisement/{advertisementId}")
    public String advertisement(Model model, HttpSession session,
                      @PathVariable("advertisementId") int id) {
        FindUser.findUser(model, session);
        model.addAttribute("advertisementById", adsService.findByIdAds(id).get());
        return "advertisement";
    }

    @GetMapping("/deleteAdvertisement/{advertisementId}")
    public String deleteAdvertisement(Model model, HttpSession session,
                                      @PathVariable("advertisementId") int id) {
        FindUser.findUser(model, session);
        model.addAttribute("advertisement", adsService.findByIdAds(id));
        return "deleteAdvertisement";
    }

    @PostMapping("/deleteAdvertisement/deleteAdvertisement")
    public String removeAds(@ModelAttribute Advertisement advertisement) {
        adsService.deleteAds(advertisement.getId());
        return "redirect:/index";
    }

    @GetMapping("/updateAdvertisement/{advertisementId}")
    public String updateAdvertisement(Model model, HttpSession session,
                            @PathVariable("advertisementId") int id) {
        FindUser.findUser(model, session);
        model.addAttribute("bodyCars", adsService.findALLBodyCar());
        model.addAttribute("marks", adsService.findAllMarkCar());
        model.addAttribute("engines", adsService.findAllEngine());
        model.addAttribute("advertisement", adsService.findByIdAds(id).get());
        return "updateAdvertisement";
    }

    @PostMapping("/updateAdvertisement")
    public String changeAds(@ModelAttribute Advertisement advertisement,
                            @ModelAttribute Car car,
                            @RequestParam (name = "engineId") int engineId,
                            @RequestParam (name = "markId") int markId,
                            @RequestParam (name = "bodyId") int bodyId,
                            @RequestParam (name = "carId") int carId,
                            @RequestParam("file") MultipartFile file)
            throws IOException {
        advertisement.setPhoto(file.getBytes());
        advertisement.setCell(false);
        changePropertiesCar(advertisement, car, engineId, markId, bodyId, carId);
        adsService.updateAds(advertisement);
        return "redirect:/index";
    }

    @GetMapping("/updateAdsStatus/{advertisementId}")
    public String updateAdsStatus(Model model, HttpSession session,
                                  @PathVariable("advertisementId") int id) {
        FindUser.findUser(model, session);
        model.addAttribute("advertisement", adsService.findByIdAds(id));
        return "updateAdsStatus";
    }

    @PostMapping("/updateAdsStatus")
    public String changeAdsStatusForTrue(@ModelAttribute Advertisement advertisement) {
        adsService.updateAdsStatus(advertisement);
        return "redirect:/index";
    }

    private void changePropertiesCar(@ModelAttribute Advertisement advertisement,
                                     @ModelAttribute Car car,
                                     @RequestParam(name = "engineId") int engineId,
                                     @RequestParam(name = "markId") int markId,
                                     @RequestParam(name = "bodyId") int bodyId,
                                     @RequestParam(name = "carId") int carId) {
        carService.addCar(car);
        advertisement.setCreated(LocalDateTime.now().withNano(0));
        car.setBodyCar(carService.findBodyCarById(bodyId).get());
        car.setMark(carService.findMarkById(markId).get());
        car.setEngine(carService.findEngineById(engineId).get());
        advertisement.setCar(carService.findCarById(carId).get());
    }
}
