package com.learnspring.boot_320.csv_to_mysql_springbatch.repo;

import com.learnspring.boot_320.csv_to_mysql_springbatch.entity.DataCsv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataBatchRepository extends JpaRepository<DataCsv, Integer> {
}
