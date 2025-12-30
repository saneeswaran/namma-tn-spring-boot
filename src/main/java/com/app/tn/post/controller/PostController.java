package com.app.tn.post.controller;

import java.util.List;
import java.util.Map;

import com.app.tn.post.entity.Post;
import com.app.tn.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    // create post
    @PostMapping("/create")
    public ResponseEntity createPost(@RequestBody Post post) {
        postService.createPost(post);
        return ResponseEntity.ok("Post created successfully");
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id, @RequestParam String userId) {
        postService.deletePost(id, userId);
        return ResponseEntity.ok("Post deleted successfully");
    }

    // update
    @PutMapping("update/{id}")
    public ResponseEntity updatePost(@PathVariable String id, @RequestBody Post post) {
        postService.updatePost(post, id);
        return ResponseEntity.ok("Post updated successfully");
    }

    // get
    @GetMapping
    public List<Post> getMethodName() {
        return postService.getAllPosts();
    }

    // pagination
    @GetMapping("/pagination")
    public Map<String, Object> postWithPagination(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size) {
        return postService.postWithPagination(page, size);
    }

}
