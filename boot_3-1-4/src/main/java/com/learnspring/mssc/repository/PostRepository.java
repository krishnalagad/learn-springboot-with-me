package com.learnspring.mssc.repository;

import com.learnspring.mssc.model.Post;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
}
