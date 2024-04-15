package com.example.blog.controller;

import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;



    @PostMapping("/api/posts")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        // Set author of the post as the currently authenticated user
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        post.setAuthor(currentPrincipalName);*/

        // Set creation date
        post.setCreatedAt(new Date());

        // Save the post
        postService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @GetMapping("/api/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/api/my-posts/{authername}")
    public ResponseEntity<List<Post>> getMyPosts(@PathVariable String authername) {

        List<Post> myPosts = postService.getPostsByAuthor(authername);
        return ResponseEntity.ok(myPosts);
    }
}
