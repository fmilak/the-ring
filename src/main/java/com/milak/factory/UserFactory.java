package com.milak.factory;

import com.milak.model.Role;
import com.milak.model.User;

import java.util.UUID;

public class UserFactory {

    public static User getAdminUser() {
        return new User.Builder().setRole(Role.ADMIN).setUuid(UUID.randomUUID().toString()).build();
    }

    public static User getRegularUser() {
        return new User.Builder().setRole(Role.USER).setUuid(UUID.randomUUID().toString()).build();
    }

}
