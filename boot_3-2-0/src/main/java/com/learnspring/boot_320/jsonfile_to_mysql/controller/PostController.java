package com.learnspring.boot_320.jsonfile_to_mysql.controller;

import com.learnspring.boot_320.jsonfile_to_mysql.DataLoader;
import com.learnspring.boot_320.jsonfile_to_mysql.entity.Post;
import com.learnspring.boot_320.jsonfile_to_mysql.repo.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private PostRepository postRepository;
    private DataLoader dataLoader;

    public PostController(PostRepository postRepository, DataLoader dataLoader) {
        this.postRepository = postRepository;
        this.dataLoader = dataLoader;
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> findAll() {
        List<Post> posts = this.postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload() {
        boolean b = this.dataLoader.saveAllData();
        if (b)
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Data saved in database"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Internal server " +
                "error happened while saving data in database"));
    }
}
