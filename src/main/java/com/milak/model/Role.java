package com.milak.model;

public enum Role {
    ADMIN("admin"),
    USER("user");

    Role(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
