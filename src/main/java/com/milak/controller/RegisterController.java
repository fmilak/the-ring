package com.milak.controller;

import com.milak.model.ApiResponse;
import com.milak.model.User;
import com.milak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign-in/")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse register(@RequestBody User newUser) {
        // todo -> move this to some controller with exposed api
        ApiResponse apiResponse = new ApiResponse();
        try {
            userService.registerUser(newUser);
            apiResponse.setMessage("User successfully created!");
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

}
