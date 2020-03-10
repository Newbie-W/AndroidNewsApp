package com.knewbie.news.entity;

import java.io.Serializable;

public class UserBean implements Serializable {
    private long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String avatar;
    private String emailAd;
    private String signature;

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserBean(int id, String username, String password, String avatar, String emailAd, String signature) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.emailAd = emailAd;
        this.signature = signature;
    }

    public UserBean() {
    }

    public UserBean(String username, String password, String avatar, String emailAd, String signature) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.emailAd = emailAd;
        this.signature = signature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmailAd() {
        return emailAd;
    }

    public void setEmailAd(String emailAd) {
        this.emailAd = emailAd;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
