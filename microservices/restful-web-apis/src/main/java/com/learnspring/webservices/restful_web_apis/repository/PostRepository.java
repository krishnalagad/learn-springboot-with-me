package com.learnspring.webservices.restful_web_apis.repository;

import com.learnspring.webservices.restful_web_apis.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}