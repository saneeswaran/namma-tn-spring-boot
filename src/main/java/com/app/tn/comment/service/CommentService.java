package com.app.tn.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.tn.comment.dto.CommentDto;
import com.app.tn.comment.entity.Comment;
import com.app.tn.comment.repository.CommentRepository;
import com.app.tn.exceptions.NotAuthorizedExceptions;
import com.app.tn.exceptions.ResourcesNotFoundException;
import com.app.tn.post.entity.Post;
import com.app.tn.post.repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // parent comment
    public void addComment(Comment comment) {
        Integer postId = comment.getPostId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourcesNotFoundException("Post not found"));

        // increment comment count
        post.setCommentCount(post.getCommentCount() + 1);
        comment.setParentCommentId(null);
        commentRepository.save(comment);
        postRepository.save(post);
    }

    // child comment

    public void AddReplyForComment(Comment comment, String parentCommentId) {
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new ResourcesNotFoundException("Comment not found"));
        // make has replies true -> so in ui part we can show reply button without
        // counting reply data
        parentComment.setHasReplies(true);
        // set parent comment id
        comment.setParentCommentId(parentCommentId);
        comment.setCommentCount(comment.getCommentCount() + 1);
        // save
        commentRepository.save(parentComment);
        commentRepository.save(comment);

    }

    // parent comment
    public void deleteComment(String commentId, String userId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourcesNotFoundException("Comment not found"));

        if (!userId.equals(comment.getUserId())) {
            throw new NotAuthorizedExceptions("You are not authorized to delete this comment");
        }

        Post post = postRepository.findById(comment.getPostId())
                .orElseThrow(() -> new ResourcesNotFoundException("Post not found"));

        // decrement comment count
        post.setCommentCount(post.getCommentCount() - 1);

        commentRepository.deleteById(commentId);
        postRepository.save(post);
    }

    // child comment

    public void deleteChildComment(String commentId, String userId) {
        // parent id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourcesNotFoundException("Comment not found"));

        if (!userId.equals(comment.getUserId())) {
            throw new NotAuthorizedExceptions("You are not authorized to delete this comment");
        }

        String parentCommentId = comment.getParentCommentId();

        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new ResourcesNotFoundException("Comment not found"));

        // decrement comment count
        parentComment.setCommentCount(parentComment.getCommentCount() - 1);

        commentRepository.deleteById(commentId);
        commentRepository.save(parentComment);

    }

    // update comment
    public Comment updateComment(Comment newComment, String userId) {
        Comment comment = commentRepository.findById(newComment.getCommentId())
                .orElseThrow(() -> new ResourcesNotFoundException("Post not found"));

        if (!comment.getUserId().equals(userId)) {
            throw new NotAuthorizedExceptions("You are not authorized to update this comment");
        }

        return commentRepository.save(comment);
    }

    public Comment updateChildComment(Comment newComment, String userId) {
        Comment comment = commentRepository.findById(newComment.getCommentId())
                .orElseThrow(() -> new ResourcesNotFoundException("Post not found"));

        if (!comment.getUserId().equals(userId)) {
            throw new NotAuthorizedExceptions("You are not authorized to update this comment");
        }

        return commentRepository.save(comment);
    }

    // get comments with paginations
    public Map<String, Object> getComments(int page, int size, Integer postId) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Comment> commentPage;

        if (postId != null) {
            commentPage = commentRepository.findByPostId(postId, pageRequest);
        } else {
            commentPage = commentRepository.findAll(pageRequest);
        }

        List<CommentDto> commentDtos = commentPage.getContent()
                .stream()
                .map(this::convertCommentDtos)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("comments", commentDtos);
        response.put("totalElements", commentPage.getTotalElements());
        response.put("totalPages", commentPage.getTotalPages());

        return response;
    }

    // get reply comments with pagination

    public Map<String, Object> getReplyComments(String parentCommentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Comment> commentPage = commentRepository.findByParentCommentId(parentCommentId, pageRequest);

        List<CommentDto> commentDtos = commentPage.getContent()
                .stream()
                .map(this::convertCommentDtos)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("comments", commentDtos);
        response.put("totalElements", commentPage.getTotalElements());
        response.put("totalPages", commentPage.getTotalPages());
        response.put("currentPage", page);

        return response;
    }

    public CommentDto convertCommentDtos(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setUserId(comment.getUserId());
        commentDto.setPostId(comment.getPostId());
        commentDto.setComment(comment.getComment());
        commentDto.setParentCommentId(comment.getParentCommentId());
        commentDto.setCommentCount(comment.getCommentCount());
        commentDto.setHasReplies(comment.isHasReplies());
        commentDto.setUserName(comment.getUserName());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }
}
