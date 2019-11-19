package com.milak.service;

import com.milak.model.User;
import com.milak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            try {
                throw new Exception("nesto"); // todo -> handle
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

}
