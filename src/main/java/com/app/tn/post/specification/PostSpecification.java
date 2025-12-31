package com.app.tn.post.specification;

import org.springframework.data.jpa.domain.Specification;
import com.app.tn.post.entity.Post;

public class PostSpecification {

    public static Specification<Post> hasName(String name) {
        return (root, query, cb) -> name == null || name.isBlank()
                ? null
                : cb.like(root.get("title"), "%" + name + "%");
    }

    public static Specification<Post> hasPriority(String priority) {
        return (root, query, cb) -> priority == null || priority.isBlank()
                ? null
                : cb.equal(root.get("priority"), priority);
    }

    public static Specification<Post> hasDescription(String description) {
        return (root, query, cb) -> description == null || description.isBlank()
                ? null
                : cb.like(root.get("description"), "%" + description + "%");
    }
}
