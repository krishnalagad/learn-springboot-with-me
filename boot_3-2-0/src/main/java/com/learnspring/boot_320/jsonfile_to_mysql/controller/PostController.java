package com.learnspring.boot_320.jsonfile_to_mysql.controller;

import com.learnspring.boot_320.jsonfile_to_mysql.entity.Post;
import com.learnspring.boot_320.jsonfile_to_mysql.repo.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> findAll() {
        List<Post> posts = this.postRepository.findAll();
        return ResponseEntity.ok(posts);
    }
}
