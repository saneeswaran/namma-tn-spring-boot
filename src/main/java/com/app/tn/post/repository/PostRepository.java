package com.app.tn.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tn.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
