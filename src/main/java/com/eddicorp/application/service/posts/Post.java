package com.eddicorp.application.service.posts;

import java.util.UUID;

public class Post {

    private final String id;
    private final String author;
    private final String title;
    private final String content;

    public Post(String author, String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.author = author;
        this.title = title;
        this.content = content;
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
