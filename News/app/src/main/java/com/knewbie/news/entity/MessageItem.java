package com.knewbie.news.entity;

public class MessageItem {
    private int id;
    private String avatarAd;
    private String uname;
    private String message;
    private String time;

    public MessageItem(int id, String avatarAd, String uname, String message, String time) {
        this.id = id;
        this.avatarAd = avatarAd;
        this.uname = uname;
        this.message = message;
        this.time = time;
    }

    public MessageItem() {
    }

    public MessageItem(String avatarAd, String uname, String message, String time) {
        this.avatarAd = avatarAd;
        this.uname = uname;
        this.message = message;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarAd() {
        return avatarAd;
    }

    public void setAvatarAd(String avatarAd) {
        this.avatarAd = avatarAd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageItem{" +
                "id=" + id +
                ", avatarAd='" + avatarAd + '\'' +
                ", uname='" + uname + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
