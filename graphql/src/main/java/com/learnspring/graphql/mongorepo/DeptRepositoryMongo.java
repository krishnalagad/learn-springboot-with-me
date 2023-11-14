package com.learnspring.graphql.mongorepo;

import com.learnspring.graphql.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeptRepositoryMongo extends MongoRepository<Department, String> {
    Optional<Department> findByDepartmentId(String id);
}