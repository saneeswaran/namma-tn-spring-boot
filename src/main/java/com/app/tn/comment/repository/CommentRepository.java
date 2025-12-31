package com.app.tn.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.app.tn.comment.dto.CommentDto;
import com.app.tn.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<CommentDto> {
    Page<Comment> findByPostId(Integer postId, Pageable pageable);

    Page<Comment> findByParentCommentId(String parentCommentId, PageRequest pageRequest);
}
