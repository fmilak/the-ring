package com.milak.repository;

import com.milak.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User findByUsername(String username) {
        return null; // todo
    }

}
