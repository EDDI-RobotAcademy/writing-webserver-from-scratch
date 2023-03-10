package com.eddicorp.application.service.post;

import com.eddicorp.application.repository.post.PostRepository;
import com.eddicorp.application.repository.post.PostRepositoryImpl;

import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository = PostRepositoryImpl.getInstance();

    @Override
    public void createPost(String author, String title, String content) {
        final Post newPost = new Post(author, title, content);
        postRepository.save(newPost);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
