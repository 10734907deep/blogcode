package com.example.blog.service;

import com.example.blog.entity.Post;

import java.util.List;

public interface PostService {
    void savePost(Post post);
    List<Post> getAllPosts();
    List<Post> getPostsByAuthor(String author);
}
