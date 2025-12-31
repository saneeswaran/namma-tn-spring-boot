package com.app.tn.comment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.tn.comment.entity.Comment;
import com.app.tn.comment.service.CommentService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // add parent comment
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResponseEntity.ok("Comment added successfully");
    }

    // add child comment
    @PostMapping("/child")
    public ResponseEntity<?> addReplyComment(@RequestBody Comment comment) {
        commentService.AddReplyForComment(comment, comment.getParentCommentId());
        return ResponseEntity.ok("Comment added successfully");
    }

    // delete parent comment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParentComment(
            @PathVariable String id,
            @RequestParam String userId) {

        commentService.deleteComment(id, userId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

    // delete child comment
    @DeleteMapping("/child/{id}")
    public ResponseEntity<?> deleteChildComment(
            @PathVariable String id,
            @RequestParam String userId) {

        commentService.deleteChildComment(id, userId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

    // update parent comment
    @PutMapping("/{id}")
    public ResponseEntity<?> updateParentComment(
            @PathVariable String id,
            @RequestBody Comment newComment) {

        commentService.updateComment(newComment, id);
        return ResponseEntity.ok("Comment updated successfully");
    }

    // update child comment
    @PutMapping("/child/{id}")
    public ResponseEntity<?> updateChildComment(
            @PathVariable String id,
            @RequestBody Comment entity) {

        commentService.updateChildComment(entity, id);
        return ResponseEntity.ok("Comment updated successfully");
    }

    // get comments by postId with pagination
    @GetMapping
    public Map<String, Object> getComments(
            @RequestParam Integer postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return commentService.getComments(page, size, postId);
    }

    // get reply comments with pagination
    @GetMapping("/child/{id}")
    public Map<String, Object> getReplyComments(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return commentService.getReplyComments(id, page, size);
    }
}
