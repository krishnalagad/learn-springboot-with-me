package com.learnspring.webservices.restful_web_apis.repository;

import com.learnspring.webservices.restful_web_apis.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}