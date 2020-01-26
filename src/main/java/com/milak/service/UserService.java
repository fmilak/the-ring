package com.milak.service;

import com.milak.model.User;
import com.milak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new Exception("User already exists");
        }
        User userBuilder = new User.Builder()
                .from(user)
                .setRegularUser()
                .setPassword(passwordEncoder.encode(user.getPassword())).build();
        userRepository.createUser(userBuilder);
    }

    public void updateUserLimit(String uuid) {
        userRepository.updateCurrentLimit(uuid);
    }

    public User findUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("User does not exist!");
        }
        return user;
    }

    public User getUserByUuid(String uuid) {
        return userRepository.getUserByUuid(uuid);
    }

}
