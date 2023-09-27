package com.learnspring.boot_320.jsonfile_to_mysql.repo;

import com.learnspring.boot_320.jsonfile_to_mysql.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
