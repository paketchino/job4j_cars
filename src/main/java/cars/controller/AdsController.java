package cars.controller;

import cars.model.*;
import cars.service.AdsService;
import cars.service.CarService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
                         @RequestParam (name = "fail", required = false)
                         Boolean fail) {
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
                            @RequestParam (name = "engineId") int engineId,
                            @RequestParam (name = "markId") int markId,
                            @RequestParam (name = "bodyId") int bodyId,
                            @RequestParam("file") MultipartFile file) throws IOException {
        advertisement.setUser((User) session.getAttribute("user"));
        Set<BodyCar> bodyCars = new HashSet<>();
        List<BodyCar> bodyCarsList = carService.findBodyCarById(bodyId);
        for (BodyCar bodyCar : bodyCarsList) {
            bodyCars.add(bodyCar);
        }
        List<Mark> markList = carService.findMarkById(markId);
        Set<Mark> marks = new HashSet<>();
        for (Mark mark : markList) {
            marks.add(mark);
        }
        Set<Engine> engines = new HashSet<>();
        List<Engine> engineList = carService.findEngineById(engineId);
        for (Engine engine : engineList) {
            engines.add(engine);
        }

        advertisement.setPhoto(file.getBytes());
        advertisement.setCreated(LocalDateTime.now().withNano(0));
        advertisement.setBodyCarSet(bodyCars);
        advertisement.setMarkSet(marks);
        advertisement.setEnginesSet(engines);
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
    public String deleteAdvertisement(Model model, HttpSession session, @PathVariable("advertisementId") int id) {
        FindUser.findUser(model, session);
        model.addAttribute("advertisement", adsService.findByIdAds(id));
        return "deleteAdvertisement";
    }

    @PostMapping("/deleteAdvertisement/deleteAdvertisement")
    public String removeAds(@ModelAttribute Advertisement advertisement) {
        adsService.deleteAds(advertisement);
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

    @PostMapping(name = "/updateAds/{advertisementId}")
    public String changeAds(@ModelAttribute Advertisement advertisement,
                            @RequestParam (name = "engineId") int engineId,
                            @RequestParam (name = "markId") int markId,
                            @RequestParam (name = "bodyId") int bodyId,
                            @RequestParam("file") MultipartFile file)
            throws IOException {
        Set<BodyCar> bodyCars = new HashSet<>();
        List<BodyCar> bodyCarsList = carService.findBodyCarById(bodyId);
        for (BodyCar bodyCar : bodyCarsList) {
            bodyCars.add(bodyCar);
        }
        List<Mark> markList = carService.findMarkById(markId);
        Set<Mark> marks = new HashSet<>();
        for (Mark mark : markList) {
            marks.add(mark);
        }
        Set<Engine> engines = new HashSet<>();
        List<Engine> engineList = carService.findEngineById(engineId);
        for (Engine engine : engineList) {
            engines.add(engine);
        }

        advertisement.setPhoto(file.getBytes());
        advertisement.setCreated(LocalDateTime.now().withNano(0));
        advertisement.setBodyCarSet(bodyCars);
        advertisement.setMarkSet(marks);
        advertisement.setEnginesSet(engines);
        adsService.updateAds(advertisement);
        return "redirect:/updateAds";
    }

    @GetMapping("/updateAdsStatus/{advertisementId}")
    public String updateAdsStatus(Model model, HttpSession session, @PathVariable("advertisementId") int id) {
        FindUser.findUser(model, session);
        model.addAttribute("advertisement", adsService.findByIdAds(id));
        return "updateAdsStatus";
    }

    @PostMapping("/updateAdsStatus/updateAdsStatus")
    public String changeAdsStatusForTrue(@ModelAttribute Advertisement advertisement) {
        adsService.updateAdsStatus(advertisement);
        return "redirect:/updateAdsStatus";
    }
}
