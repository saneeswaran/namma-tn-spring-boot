package com.app.tn.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tn.post.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {

}
