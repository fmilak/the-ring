package com.milak.model;

import com.milak.factory.UserFactory;
import org.codehaus.jackson.annotate.JsonIgnore;

public class User { // todo -> use freebuilder

    private String uuid;

    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String surname;

    private String email;

    private Role role;

    public User() {}

    private User(Builder builder) {
        this.uuid = builder.uuid;
        this.username = builder.username;
        this.password = builder.password;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.role = builder.role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static class Builder {

        private String uuid;

        private String username;

        private String password;

        private String name;

        private String surname;

        private String email;

        private Role role;

        public Builder from(User user) {
            this.uuid = user.uuid;
            this.username = user.username;
            this.password = user.password;
            this.name = user.name;
            this.surname = user.surname;
            this.email = user.email;
            this.role = user.role;
            return this;
        }

        public Builder setRegularUser() {
            User user = UserFactory.getRegularUser();
            this.uuid = user.getUuid();
            this.role = user.getRole();
            return this;
        }

        public Builder setAdminUser() {
            User user = UserFactory.getRegularUser();
            this.uuid = user.getUuid();
            this.role = user.getRole();
            return this;
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}
