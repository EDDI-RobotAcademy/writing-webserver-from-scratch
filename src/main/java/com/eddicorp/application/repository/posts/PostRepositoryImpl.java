package com.eddicorp.application.repository.posts;

import com.eddicorp.application.service.posts.Post;

import java.util.ArrayList;
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
        postDb.put(post.getId(), post);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(postDb.values());
    }
}
