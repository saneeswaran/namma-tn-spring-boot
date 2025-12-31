package com.app.tn.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tn.post.entity.Priority;
import com.app.tn.post.repository.PriorityRepository;

@Service
public class PriorityService {
    @Autowired
    private PriorityRepository priorityRepository;

    public void createPriority(Priority priority) {
        priorityRepository.save(priority);

    }

    public void deletePriority(Integer id) {
        priorityRepository.deleteById(id);
    }

    public Priority updatePriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    public List<Priority> getAllPriority() {
        return priorityRepository.findAll();
    }
}
