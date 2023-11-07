package com.erikhillard.sheepgame.repository;

import com.erikhillard.sheepgame.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
