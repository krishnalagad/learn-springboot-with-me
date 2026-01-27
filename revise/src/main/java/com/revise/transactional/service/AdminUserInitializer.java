package com.revise.transactional.service;

import com.revise.transactional.entity.SpringSecurityUser;
import com.revise.transactional.repo.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

//    @Bean
//    public CommandLineRunner commandLineRunner(UserDetailsRepository userDetailsRepository,
//                                               PasswordEncoder passwordEncoder) {
//        return args -> {
//            if (userDetailsRepository.findByUsername("admin").isEmpty()) {
//                SpringSecurityUser user = new SpringSecurityUser();
//                user.setUsername("admin");
//                user.setPassword(passwordEncoder.encode("admin123"));
//                user.setRole("ROLE_ADMIN");
//
//                userDetailsRepository.save(user);
//                System.out.println("Default admin user created!!");
//            }
//        };
//    }
}