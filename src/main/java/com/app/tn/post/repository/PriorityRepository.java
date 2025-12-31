package com.app.tn.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tn.post.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {

}
