package com.example.blog.service;

import com.example.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);
    List<Comment> getCommentsByPostId(String postId);
}
