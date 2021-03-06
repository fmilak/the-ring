package com.milak.controller;

import com.milak.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public String showHome(Model model) {
        List<String> outcomesList = new ArrayList<>();
        outcomesList.add("outcome-1");
        outcomesList.add("outcome-2");
        outcomesList.add("outcome-3");
        outcomesList.add("outcome-4");
        outcomesList.add("outcome-5");

        User user = new User();
        user.setName("name");
        user.setSurname("surname");
        model.addAttribute("outcomesList", outcomesList);
        model.addAttribute("user", user);
        return "home";
    }

    @PostMapping(value = "/update")
    public String navigateToRealFrontend(@ModelAttribute("user") User user, Model model) {

        user.setUsername("username");
        model.addAttribute("user", user);
        return "home";
    }

}
