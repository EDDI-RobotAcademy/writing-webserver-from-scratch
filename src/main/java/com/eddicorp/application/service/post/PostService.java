package com.eddicorp.application.service.post;

import java.util.List;

public interface PostService {

    void createPost(String author, String title, String content);

    List<Post> getAllPosts();

}
