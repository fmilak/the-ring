package com.milak.model;

public class Post {

    private int id;
    private String text;
    private byte[] picture;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post prototype() {
        Post newPost = new Post();
        newPost.setId(this.id);
        newPost.setUser(this.user);
        newPost.setText(this.text);
        newPost.setPicture(this.picture);
        return newPost;
    }
}
