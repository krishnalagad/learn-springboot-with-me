package com.aop.user.aspects;

import com.aop.user.entity.User;
import com.aop.user.repository.UserRepository;
import com.aop.user.service.UserService;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Aspect
@NoArgsConstructor
@ToString
public class UserAspect {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

//    public UserAspect(User user) {
//        this.user = user;
//    }

//    @After("execution(* com.aop.user.service.impl.UserServiceImpl.createUser(..))")
//    public void setCreate() {
//        System.out.println(this.user);
//
////        this.userService.getUser(this.)
//        System.out.println("End of setCreate()");
//    }

    @AfterReturning(pointcut = "execution(* com.aop.user.service.impl.UserServiceImpl.createUser(..))", returning = "user")
    public void setAuditFields(User user) {
        Instant currentDate = Instant.now();
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(currentDate);
        }
        user.setUpdatedAt(currentDate);
        System.out.println(user);
        this.userRepository.save(user);
    }

}
