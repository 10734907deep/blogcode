package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/api/comments")
    public ResponseEntity<String> createComment(@RequestBody Comment comment) {
        // Set author of the comment as the currently authenticated user
       /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        comment.setAuthor(currentPrincipalName);*/

        // Set creation date
        comment.setCreatedAt(new Date());

        // Save the comment
        commentService.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
    }

    @GetMapping("/api/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable String postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}