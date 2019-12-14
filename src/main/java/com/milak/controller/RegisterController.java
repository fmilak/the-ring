package com.milak.controller;

import com.milak.model.ApiResponse;
import com.milak.model.Role;
import com.milak.model.User;
import com.milak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sign-in/")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse register(@RequestBody User newUser) {
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

    @GetMapping("/roles")
    public ApiResponse getRoles() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setData(Role.values());
        return apiResponse;
    }

}
