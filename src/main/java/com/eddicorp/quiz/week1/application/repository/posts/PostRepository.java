package com.eddicorp.quiz.week1.application.repository.posts;

import com.eddicorp.quiz.week1.application.service.posts.Post;

import java.util.List;

public interface PostRepository {

    void save(Post post);
    List<Post> findAll();
}
