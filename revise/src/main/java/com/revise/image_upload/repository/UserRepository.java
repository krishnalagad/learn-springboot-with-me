package com.revise.image_upload.repository;

import com.revise.image_upload.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
