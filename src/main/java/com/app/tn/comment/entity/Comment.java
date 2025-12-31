package com.app.tn.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_id")
    private String commentId;
    @Column(name = "post_id")
    @NotNull(message = "postId is required")
    private Integer postId;
    @Column(name = "user_id")
    @NotNull(message = "userId is required")
    private String userId;
    @Column(name = "comment")
    @NotBlank(message = "comment is required")
    private String comment;
    @Column(name = "user_name")
    @NotBlank(message = "userName is required")
    private String userName;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "has_replies", nullable = true)
    private boolean hasReplies;
    @Column(name = "hidden_by_admin", nullable = true)
    private boolean hiddenByAdmin;
    @Column(name = "comment_count", nullable = true)
    private Integer commentCount = 0;
    @Column(name = "parent_comment_id", nullable = true)
    private String parentCommentId;

    public Comment() {
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isHasReplies() {
        return hasReplies;
    }

    public void setHasReplies(boolean hasReplies) {
        this.hasReplies = hasReplies;
    }

    public boolean isHiddenByAdmin() {
        return hiddenByAdmin;
    }

    public void setHiddenByAdmin(boolean hiddenByAdmin) {
        this.hiddenByAdmin = hiddenByAdmin;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
}
