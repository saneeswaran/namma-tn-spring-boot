package com.app.tn.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tn.comment.entity.Comment;
import com.app.tn.comment.repository.CommentRepository;
import com.app.tn.exceptions.ResourcesNotFoundException;
import com.app.tn.post.entity.Post;
import com.app.tn.post.repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public void addComment(Comment comment) {
        Integer postId = comment.getPostId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourcesNotFoundException("Post not found"));
        // adding comment count in post table
        post.setCommentCount(+1);
        commentRepository.save(comment);
        postRepository.save(post);
    }

    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }
}
