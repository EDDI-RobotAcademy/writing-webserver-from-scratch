package com.eddicorp.quiz.week1.application.service.posts;

import java.util.List;

public interface PostService {

    void write(String author, String title, String content);

    List<Post> getAllPosts();
}
