package com.eddicorp.application.repository.post;

import com.eddicorp.application.service.post.Post;

import java.util.List;

public interface PostRepository {
    void save(Post post);
    List<Post> findAll();
}
