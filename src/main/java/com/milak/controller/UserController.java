package com.milak.controller;

import com.milak.model.ApiResponse;
import com.milak.model.User;
import com.milak.security.LoginUserService;
import com.milak.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ApiResponse getUser(@PathVariable String username) {
        User user = userService.findUserByUsername(username);

        ObjectMapper mapper = new ObjectMapper();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        try {
            apiResponse.setData(mapper.writeValueAsString(user));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiResponse;
    }
}
