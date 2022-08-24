package jo4j_cars.controller;

import jo4j_cars.model.Advertisement;
import jo4j_cars.service.AdsService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }


    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("advertisements", adsService.findAll());
        FindUser.findUser(model, session);
        return "index";
    }

    @GetMapping("/addAds")
    public String addAds(Model model, HttpSession session) {
        FindUser.findUser(model, session);
        return "addAds";
    }

    @PostMapping("/createAds")
    public String createAds(@ModelAttribute Advertisement advertisement) {
        adsService.addAds(advertisement);
        return "redirect:/index";
    }

    @GetMapping(name = "/advertisement/{advertisementId}")
    public String ads(Model model, HttpSession session,
                      @PathVariable("advertisementId") int id) {
        model.addAttribute("advertisementById", adsService.findByIdAds(id).get());
        FindUser.findUser(model, session);
        return "advertisement";
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

}
