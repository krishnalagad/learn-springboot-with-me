package com.learnspring.graphql.aspects;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.entity.Employee;
import com.learnspring.graphql.repository.DeptRepository;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Aspect
@NoArgsConstructor
@ToString
public class DeptAspect {

    @Autowired
    private DeptRepository deptRepository;

    @AfterReturning(pointcut = "execution(* com.learnspring.graphql.service.impl.DeptServiceImpl.create(..))", returning = "department")
    public void setAuditFields(Department department) {
        Instant currentDate = Instant.now();
        if (department.getCreatedAt() == null) {
            department.setCreatedAt(currentDate);
        }
        department.setUpdatedAt(currentDate);
        this.deptRepository.save(department);
    }

    @AfterReturning(pointcut = "execution(* com.learnspring.graphql.service.impl.DeptServiceImpl.update(..))", returning = "department")
    public void setUpdateDate(Department department) {
        Instant currentDate = Instant.now();
        department.setUpdatedAt(currentDate);
        this.deptRepository.save(department);
    }
}