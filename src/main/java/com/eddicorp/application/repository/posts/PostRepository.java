package com.eddicorp.application.repository.posts;

import com.eddicorp.application.service.posts.Post;

import java.util.List;

public interface PostRepository {

    void save(Post post);
    List<Post> findAll();
}
