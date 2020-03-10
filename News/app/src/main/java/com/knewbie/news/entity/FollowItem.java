package com.knewbie.news.entity;

public class FollowItem {
    private int id;
    //还有头像
    private String uName;
    private String selfIntroduction;
    private String avatarAd;

    public FollowItem(int id, String uName, String selfIntroduction, String avatarAd) {
        this.id = id;
        this.uName = uName;
        this.selfIntroduction = selfIntroduction;
        this.avatarAd = avatarAd;
    }

    public FollowItem() {
    }

    public FollowItem(String uName, String selfIntroduction, String avatarAd) {
        this.uName = uName;
        this.selfIntroduction = selfIntroduction;
        this.avatarAd = avatarAd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public String getAvatarAd() {
        return avatarAd;
    }

    public void setAvatarAd(String avatarAd) {
        this.avatarAd = avatarAd;
    }

    @Override
    public String toString() {
        return "FollowItem{" +
                "id=" + id +
                ", uName='" + uName + '\'' +
                ", selfIntroduction='" + selfIntroduction + '\'' +
                ", avatarAd='" + avatarAd + '\'' +
                '}';
    }
}
