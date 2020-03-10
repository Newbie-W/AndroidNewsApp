package com.knewbie.news.entity;

public class NewsForReadItem {
    private int id;
    private String title;
    private String introduction;    //引言，或为内容的前多少字，可去
    private String category;
    private String content;
    private String author;
    private String lastEditTime;
    private String pic;
    private int readAmount;
    private int reviewAmount;
    private int likeAmount;

    public NewsForReadItem(int id, String title, String introduction, String category, String content, String author, String lastEditTime, String pic, int readAmount, int reviewAmount, int likeAmount) {
        this.id = id;
        this.title = title;
        this.introduction = introduction;
        this.category = category;
        this.content = content;
        this.author = author;
        this.lastEditTime = lastEditTime;
        this.pic = pic;
        this.readAmount = readAmount;
        this.reviewAmount = reviewAmount;
        this.likeAmount = likeAmount;
    }

    public NewsForReadItem() {
    }

    public NewsForReadItem(String title, String introduction, String category, String content, String author, String lastEditTime, String pic, int readAmount, int reviewAmount, int likeAmount) {
        this.title = title;
        this.introduction = introduction;
        this.category = category;
        this.content = content;
        this.author = author;
        this.lastEditTime = lastEditTime;
        this.pic = pic;
        this.readAmount = readAmount;
        this.reviewAmount = reviewAmount;
        this.likeAmount = likeAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(String lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getReadAmount() {
        return readAmount;
    }

    public void setReadAmount(int readAmount) {
        this.readAmount = readAmount;
    }

    public int getReviewAmount() {
        return reviewAmount;
    }

    public void setReviewAmount(int reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public int getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(int likeAmount) {
        this.likeAmount = likeAmount;
    }

    @Override
    public String toString() {
        return "NewsForReadItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", lastEditTime='" + lastEditTime + '\'' +
                ", pic='" + pic + '\'' +
                ", readAmount=" + readAmount +
                ", reviewAmount=" + reviewAmount +
                ", likeAmount=" + likeAmount +
                '}';
    }
}
