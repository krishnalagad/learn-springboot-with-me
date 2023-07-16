package com.aop.user.aspects;

import com.aop.user.repository.UserRepository;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {

    @Autowired
    private UserRepository userRepository;

    public void setCreate() {

    }
}
