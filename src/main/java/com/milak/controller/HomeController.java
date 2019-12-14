package com.milak.controller;

import com.milak.model.ApiResponse;
import com.milak.model.User;
import com.milak.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public String showHome(Model model) {
        List<String> outcomesList = new ArrayList<>();
        outcomesList.add("Outcome 1");
        outcomesList.add("Outcome 2");
        outcomesList.add("Outcome 3");
        outcomesList.add("Outcome 4");
        outcomesList.add("Outcome 5");

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
