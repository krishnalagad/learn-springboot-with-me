package com.revise.transactional.service;

import com.revise.transactional.entity.Role;
import com.revise.transactional.entity.SpringSecurityUser;
import com.revise.transactional.repo.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer{
   @Bean
    public CommandLineRunner initializeUser(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
       return args -> {
           // Adding test normal user
           if (userDetailsRepository.findByUsername("user").isEmpty()) {
               SpringSecurityUser user = new SpringSecurityUser();
               user.setUsername("user");
               user.setPassword(passwordEncoder.encode("user123"));
               user.setRole(Role.USER);
               user.setDeleted(false);

               userDetailsRepository.save(user);
               System.out.println("Default normal user created!!");
           }

           // Adding test admin user
           if (userDetailsRepository.findByUsername("admin").isEmpty()) {
               SpringSecurityUser user = new SpringSecurityUser();
               user.setUsername("admin");
               user.setPassword(passwordEncoder.encode("admin123"));
               user.setRole(Role.ADMIN);
               user.setDeleted(false);

               userDetailsRepository.save(user);
               System.out.println("Default admin user created!!");
           }
       };
   }
}