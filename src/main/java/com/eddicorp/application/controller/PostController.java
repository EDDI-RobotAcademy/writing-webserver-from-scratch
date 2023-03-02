package com.eddicorp.application.controller;

import com.eddicorp.application.service.post.Post;
import com.eddicorp.application.service.post.PostService;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostController {
    private PostService service;
    private Post post;
    private String author;
    private String title;
    private String content;

    public PostController(String body){
        String[] separateBody = body.split("&");
        String[] SeparateAuthor = separateBody[0].split("=");
        String[] separateTitle = separateBody[1].split("=");
        String[] separateContent = separateBody[2].split("=");
        this.author = SeparateAuthor[1].trim();
        this.title = separateTitle[1].trim();
        this.content = separateContent[1].trim();

        this.post = new Post(author,title,content);
    }

    public String getPostList(byte[] readFileData){

        final Mustache.Compiler compiler = Mustache.compiler();
        final String templateStringFromIndexHtmlFile = new String(readFileData, StandardCharsets.UTF_8);

        final Template template =
                compiler.compile(templateStringFromIndexHtmlFile);

       List<Post> getPost = service.getAllPosts();
       final Map<String, Object> context = new HashMap<>();
        context.put("posts", getPost);

        String renderedPage = "null";
        renderedPage = template.execute(context);

        return renderedPage;
    }

    public void setPost(){

    }

}
