package com.learnspring.graphql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aulacube_dept")
@Document(collection = "aulacube_dept")
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
    @JsonIgnore
    private List<Employee> employees = new ArrayList<>();

}