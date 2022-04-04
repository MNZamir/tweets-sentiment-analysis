package com.example.application.controller;

public class User {
    private String name;
    private String id;
    private String username;
    private String profile_pic_url;

    public User() {}

    public User(String name, String id, String username, String profile_pic_url) {
        this.name = name;
        this.id = id;
        this.username = username;
        this.profile_pic_url = profile_pic_url;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }
}
