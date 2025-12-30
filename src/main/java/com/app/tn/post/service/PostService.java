package com.app.tn.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tn.exceptions.NotAuthorizedExceptions;
import com.app.tn.exceptions.ResourcesNotFoundException;
import com.app.tn.post.entity.Post;
import com.app.tn.post.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // get
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        if (!posts.isEmpty()) {
            throw new ResourcesNotFoundException("No posts found");
        }
        return posts;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // update
    public Post updatePost(Post post, String userId) {
        Integer postId = post.getId();
        Post existsPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourcesNotFoundException("Post not found"));

        String postUserId = existsPost.getUserId();

        if (!userId.equals(postUserId)) {
            throw new NotAuthorizedExceptions("You are not authorized to update this post");
        }

        if (existsPost != null) {
            existsPost.setTitle(post.getTitle());
            existsPost.setDescription(post.getDescription());
            existsPost.setAdditionalInformation(post.getAdditionalInformation());
            existsPost.setPriority(post.getPriority());

            existsPost.setCommentCount(post.getCommentCount());
            existsPost.setLocation(post.getLocation());
            existsPost.setImages(post.getImages());
            postRepository.save(existsPost);
        }
        return postRepository.save(existsPost);

    }

    // delete
    public void deletePost(Integer postId, String userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourcesNotFoundException("Post not found"));

        String postUserId = post.getUserId();

        if (!userId.equals(postUserId)) {
            throw new NotAuthorizedExceptions("You are not authorized to delete this post");
        }

        postRepository.delete(post);
    }

    // verify by admin
    public Post verifyPost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourcesNotFoundException("Post not found"));
        post.setVerifiedByAdmin(true);
        return postRepository.save(post);
    }

    // breaking news
    public Post setBreakingNews(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourcesNotFoundException("Post not found"));
        post.setBreakingNews(true);
        return postRepository.save(post);
    }

}
