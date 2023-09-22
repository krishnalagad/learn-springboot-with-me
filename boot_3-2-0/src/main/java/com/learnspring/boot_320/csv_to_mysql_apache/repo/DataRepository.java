package com.learnspring.boot_320.csv_to_mysql_apache.repo;

import com.learnspring.boot_320.csv_to_mysql_apache.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Integer> {
}
