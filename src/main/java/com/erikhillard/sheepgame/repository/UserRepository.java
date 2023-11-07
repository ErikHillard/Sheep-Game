package com.erikhillard.sheepgame.repository;

import com.erikhillard.sheepgame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
