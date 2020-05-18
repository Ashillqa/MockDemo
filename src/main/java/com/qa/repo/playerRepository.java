package com.qa.repo;

import com.qa.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface playerRepository extends JpaRepository<Player,Long> {
    Player findByName(String name);

}
