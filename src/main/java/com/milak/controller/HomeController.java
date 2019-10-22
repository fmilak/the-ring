package com.milak.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("nesto", "nesto");
        return "home";
    }

    @PostMapping(value = "goHome")
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
