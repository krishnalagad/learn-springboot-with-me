package com.learnspring.mssc.controller;

import com.learnspring.mssc.model.Post;
import com.learnspring.mssc.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> findAll() {
        List<Post> posts = this.postRepository.findAll();
        return ResponseEntity.ok(posts);
    }
}
