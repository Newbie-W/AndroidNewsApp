package com.knewbie.news.entity;

public class NewsDisplayItem {
    private int id;
    private String title;
    private String category;
    //private String introduction;
    private String content;
    private String author;
    private String lastEditTime;
    //private String pic;

    public NewsDisplayItem(int id, String title, String category, String content, String author, String lastEditTime) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.author = author;
        this.lastEditTime = lastEditTime;
    }

    public NewsDisplayItem() {
    }

    public NewsDisplayItem(String title, String category, String content, String author, String lastEditTime) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.author = author;
        this.lastEditTime = lastEditTime;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /*public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }*/

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

    /*public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }*/

    @Override
    public String toString() {
        return "NewsDisplayItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                //", introduction='" + introduction + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", lastEditTime='" + lastEditTime + '\'' +
               // ", pic='" + pic + '\'' +
                '}';
    }
}
