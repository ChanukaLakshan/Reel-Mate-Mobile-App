package com.example.newreelmate.models;

public class Review {
    private int id;
    private String userName;
    private String userAvatar;
    private int rating;
    private String comment;
    private int likes;
    private String date;
    private boolean isLiked;

    public Review(int id, String userName, String userAvatar, int rating,
                  String comment, int likes, String date) {
        this.id = id;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.rating = rating;
        this.comment = comment;
        this.likes = likes;
        this.date = date;
        this.isLiked = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}

