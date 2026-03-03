package com.learnspring.etl.loaddatainfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadDataProductRepository extends JpaRepository<LoadDataProduct, Long> {
}
