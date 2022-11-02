package ru.joj4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.joj4j.cars.model.BodyCar;
import ru.joj4j.cars.model.Engine;
import ru.joj4j.cars.model.Mark;
import ru.joj4j.cars.service.AdsService;
import ru.joj4j.cars.service.CarService;
import ru.joj4j.cars.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CarController {

    private final CarService carService;
    private final AdsService adsService;
    private final UserService userService;

    @GetMapping("/addEngine")
    public String addEngine(HttpSession session, Model model,
                            @RequestParam (name = "fail", required = false)
                            Boolean fail) {
        model.addAttribute("fail", fail != null);
        FindUser.findUser(model, session);
        return "addEngine";
    }

    @PostMapping("/createEngine")
    public String createEngine(@ModelAttribute Engine engine, Model model) {
        Optional createEngine = carService.addEngine(engine);
        if (createEngine.isEmpty()) {
            model.addAttribute("message", "Двигатель уже существует");
            return "redirect:/fail";
        }
        return "redirect:/index";
    }

    @GetMapping("addMark")
    public String addMark(HttpSession session, Model model,
                          @RequestParam(name = "fail", required = false)
                          Boolean fail) {
        model.addAttribute("fail", fail != null);
        FindUser.findUser(model, session);
        return "addMark";
    }

    @PostMapping("/createMark")
    public String createMark(@ModelAttribute Mark mark, Model model) {
        Optional createMark = carService.addMark(mark);
        if (createMark.isEmpty()) {
            model.addAttribute("message", "Марка уже была добавлена");
            return "redirect:/fail";
        }
        return "redirect:/index";
    }

    @GetMapping("/addBodyCar")
    public String addBodyCar(HttpSession session, Model model,
                             @RequestParam (name = "fail", required = false)
                             Boolean fail) {
        model.addAttribute("fail", fail != null);
        FindUser.findUser(model, session);
        return "addBodyCar";
    }

    @PostMapping("/createBodyCar")
    public String createBodyCar(@ModelAttribute BodyCar bodyCar, Model model) {
        Optional createBodyCar = carService.addBodyCar(bodyCar);
        if (createBodyCar.isEmpty()) {
            model.addAttribute("message", "Данный тип кузова уже был добавлен");
            return "redirect:/fail";
        }
        return "redirect:/index";
    }

}
