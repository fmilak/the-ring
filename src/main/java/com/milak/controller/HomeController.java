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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("nesto", "nesto");
        return "home";
    }

    @PostMapping(value = "/goHome")
    public void navigateToRealFrontend(Model model) {
        System.out.println("todo");
        System.out.println(model);

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://localhost:3000");

        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
