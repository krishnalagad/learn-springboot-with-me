package com.learnspring.graphql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aulacube_dept")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    private String departmentId;
    private String departmentName;
    private Instant createdAt;
    private Instant updatedAt;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

}