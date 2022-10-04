package ru.joj4j.cars.controller;

import ru.joj4j.cars.model.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public class FindUser {

    public static void findUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setNameOne("Гость");
        }
        model.addAttribute("user", user);
    }
}
