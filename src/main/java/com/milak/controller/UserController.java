package com.milak.controller;

import com.milak.model.ApiResponse;
import com.milak.model.User;
import com.milak.security.LoginUserService;
import com.milak.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ApiResponse getUser(@PathVariable String username) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            User user = userService.findUserByUsername(username);
            ObjectMapper mapper = new ObjectMapper();
            apiResponse.setSuccess(true);
            apiResponse.setData(mapper.writeValueAsString(user));
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
        }

        return apiResponse;
    }
}
