package com.aop.user.aspects;

import com.aop.user.entity.User;
import com.aop.user.repository.UserRepository;
import com.aop.user.service.UserService;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserAspect.class);

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

    // AOP aspect to set date of creation of user
    @AfterReturning(pointcut = "execution(* com.aop.user.service.impl.UserServiceImpl.createUser(..))", returning = "user")
    public void setAuditFields(User user) {
        Instant currentDate = Instant.now();
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(currentDate);
        }
        user.setUpdatedAt(currentDate);
        this.userRepository.save(user);
    }

    @AfterReturning(pointcut = "execution(* com.aop.user.service.impl.UserServiceImpl.updateUser(..))", returning = "user")
    public void setUpdateDate(User user) {
        Instant currentDate = Instant.now();
        user.setUpdatedAt(currentDate);
        this.userRepository.save(user);
    }

}
