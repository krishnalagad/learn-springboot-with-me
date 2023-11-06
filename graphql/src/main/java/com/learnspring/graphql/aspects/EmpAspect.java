package com.learnspring.graphql.aspects;

import com.learnspring.graphql.entity.Employee;
import com.learnspring.graphql.repository.EmpRepository;
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
public class EmpAspect {

    @Autowired
    private EmpRepository empRepository;

    @AfterReturning(pointcut = "execution(* com.learnspring.graphql.service.impl.EmpServiceImpl.create(..))", returning = "employee")
    public void setAuditFields(Employee employee) {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata"); // Use the ZoneId for Indian Standard Time
        ZonedDateTime currentDate = ZonedDateTime.now(zoneId);
        if (employee.getCreatedAt() == null) {
            employee.setCreatedAt(currentDate.toInstant());
        }
        employee.setUpdatedAt(currentDate.toInstant());
        this.empRepository.save(employee);
    }

    @AfterReturning(pointcut = "execution(* com.learnspring.graphql.service.impl.EmpServiceImpl.update(..))", returning = "employee")
    public void setUpdateDate(Employee employee) {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata"); // Use the ZoneId for Indian Standard Time
        ZonedDateTime currentDate = ZonedDateTime.now(zoneId);
        employee.setUpdatedAt(currentDate.toInstant());
        this.empRepository.save(employee);
    }
}