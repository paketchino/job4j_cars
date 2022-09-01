package jo4j_cars.controller;

import jo4j_cars.model.*;
import jo4j_cars.service.AdsService;
import jo4j_cars.service.CarService;
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
import java.util.*;

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
    public ResponseEntity<Resource> download(@PathVariable("advertisementId") Integer advertisementId) {
        Optional<Advertisement> advertisement = adsService.findByIdAds(advertisementId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(advertisement.get().getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(advertisement.get().getPhoto()));
    }

    @GetMapping(name = "/advertisement/{advertisementId}")
    public String ads(Model model, HttpSession session,
                      @PathVariable("advertisementId") int id) {
        model.addAttribute("advertisementById", adsService.findByIdAds(id).get());
        FindUser.findUser(model, session);
        return "advertisement";
    }

}
