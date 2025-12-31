package com.app.tn.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDto {
    private String commentId;
    @NotNull(message = "postId is required")
    private Integer postId;
    @NotNull(message = "userId is required")
    private String userId;
    @NotBlank(message = "comment is required")
    private String comment;
    @NotBlank(message = "userName is required")
    private String userName;
    private String createdAt;
    private boolean hasReplies;
    private boolean hiddenByAdmin;
    private Integer commentCount = 0;
    private String parentCommentId;

    public CommentDto() {
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
