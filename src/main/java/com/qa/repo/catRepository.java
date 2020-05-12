package com.qa.repo;

import com.qa.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface catRepository extends JpaRepository<Category,Long> {
    Category findByLevel (String level);
}
