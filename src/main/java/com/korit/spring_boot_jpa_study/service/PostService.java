package com.korit.spring_boot_jpa_study.service;

import com.korit.spring_boot_jpa_study.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
}
