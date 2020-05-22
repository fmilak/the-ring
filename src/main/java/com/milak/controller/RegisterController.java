package com.milak.controller;

import com.milak.model.ApiResponse;
import com.milak.model.Role;
import com.milak.model.User;
import com.milak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sign-in/")
public class RegisterController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User newUser) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            jmsTemplate.convertAndSend("Creating user with username: " + newUser.getUsername());
            userService.registerUser(newUser);
            apiResponse.setMessage("User successfully created!");
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<ApiResponse>(apiResponse, headers, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ApiResponse getRoles() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setData(Role.values());
        return apiResponse;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        jmsTemplate.convertAndSend("Fetching all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUserByUsername(@PathVariable String username) {
        jmsTemplate.convertAndSend("Deleting user with username: " + username);
        userService.deleteUser(username);
    }

    @PutMapping(value = "/update")
    public void updateUser(@RequestBody User updatedUser) {
        jmsTemplate.convertAndSend("Updating user with username: " + updatedUser.getUsername());
        userService.updateUser(updatedUser);
    }

}
