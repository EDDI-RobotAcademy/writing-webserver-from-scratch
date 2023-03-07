package com.eddicorp.application.repository.post;

import com.eddicorp.application.service.post.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepositoryImpl implements PostRepository {

    private static final PostRepository INSTANCE = new PostRepositoryImpl();

    public static PostRepository getInstance() {
        return INSTANCE;
    }

    private PostRepositoryImpl() {
    }

    private final Map<String, Post> postDb = new HashMap<>();

    @Override
    public void save(Post post) {

    }

    @Override
    public List<Post> findAll() {
        return null;
    }
}
