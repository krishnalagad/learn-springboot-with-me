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
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@Aspect
@NoArgsConstructor
@ToString
public class DeptAspect {

    @Autowired
    private DeptRepository deptRepository;

    @AfterReturning(pointcut = "execution(* com.learnspring.graphql.service.impl.DeptServiceImpl.create(..))", returning = "department")
    public void setAuditFields(Department department) {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata"); // Use the ZoneId for Indian Standard Time
        ZonedDateTime currentDate = ZonedDateTime.now(zoneId);
        if (department.getCreatedAt() == null) {
            department.setCreatedAt(currentDate.toInstant());
        }
        department.setUpdatedAt(currentDate.toInstant());
        this.deptRepository.save(department);
    }

    @AfterReturning(pointcut = "execution(* com.learnspring.graphql.service.impl.DeptServiceImpl.update(..))", returning = "department")
    public void setUpdateDate(Department department) {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata"); // Use the ZoneId for Indian Standard Time

        ZonedDateTime currentDate = ZonedDateTime.now(zoneId);
        department.setUpdatedAt(currentDate.toInstant());
        this.deptRepository.save(department);
    }
}