package com.eddicorp.application.service.post;

import com.eddicorp.application.repository.post.PostRepository;
import com.eddicorp.application.repository.post.PostRepositoryImpl;

import java.util.List;

public class PostServiceImpl implements PostService{
    private final PostRepository repository = PostRepositoryImpl.getInstance();

    @Override
    public void write(String author, String title, String content) {
        final Post newPost = new Post(author, title, content);
        repository.save(newPost);
    }

    @Override
    public List<Post> getAllPosts() {
        return repository.findAll();
    }
}
