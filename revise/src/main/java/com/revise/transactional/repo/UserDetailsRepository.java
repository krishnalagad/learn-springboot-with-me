package com.revise.transactional.repo;

import com.revise.transactional.entity.SpringSecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<SpringSecurityUser, Long> {

    Optional<SpringSecurityUser> findByUsername(String username);
}