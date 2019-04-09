package com.studioseven.dsc_chat.Models;

public class User {
    String name;
    String status;
    String photoUrl;

    public User(String name, String status, String photoUrl) {
        this.name = name;
        this.status = status;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
