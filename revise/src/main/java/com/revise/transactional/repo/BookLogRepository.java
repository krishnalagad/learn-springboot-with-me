package com.revise.transactional.repo;

import com.revise.transactional.entity.BookLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLogRepository extends JpaRepository<BookLog, Long> {
}