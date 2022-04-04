package com.example.application.controller;

public class Tweet {
    private String id;
    private String text;
    private String user;
    private String createdAt;
    private String author_id;
    private User author;

    public Tweet() {}

    public Tweet(String id, String text, String user, String createdAt, String author_id) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
        this.author_id = author_id;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

