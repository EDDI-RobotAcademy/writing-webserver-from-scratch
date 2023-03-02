package com.eddicorp.application.service.post;

import java.util.UUID;

public class Post {

    private String id;
    private String author;
    private String title;
    private String content;

    public Post(String author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
