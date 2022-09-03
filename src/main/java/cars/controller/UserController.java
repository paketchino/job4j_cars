package cars.controller;

import cars.model.User;
import cars.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    public String addUser(HttpSession session, Model model,
                          @RequestParam (name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        FindUser.findUser(model, session);
        return "addUser";
    }

    @GetMapping("/users")
    public String usersForAdmin(HttpSession session, Model model) {
        FindUser.findUser(model, session);
        model.addAttribute("users", userService.users());
        return "users";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user, Model model) {
        Optional regAcc = userService.addUser(user);
        if (regAcc.isEmpty()) {
            model.addAttribute("message", "Пользователь уже существует");
            return "redirect:/fail";
        }
        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model,
                            @RequestParam(name = "fail", required = false) Boolean fail,
                            HttpSession session) {
        model.addAttribute("fail", fail != null);
        FindUser.findUser(model, session);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userStore = userService.findLoginAndPassword(
                user.getLogin(), user.getPassword()
        );
        if (userStore.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userStore.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        FindUser.findUser(model, session);
        session.invalidate();
        return "redirect:/loginPage";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }
}
