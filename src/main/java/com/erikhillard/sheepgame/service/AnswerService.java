package com.erikhillard.sheepgame.service;

import com.erikhillard.sheepgame.dto.AnswerRequest;
import com.erikhillard.sheepgame.dto.AnswerResponse;
import com.erikhillard.sheepgame.model.Answer;
import com.erikhillard.sheepgame.model.User;
import com.erikhillard.sheepgame.repository.AnswerRepository;
import com.erikhillard.sheepgame.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final UserService userService;


    @Autowired
    public AnswerService(AnswerRepository answerRepository, UserService userService) {
        this.answerRepository = answerRepository;
        this.userService = userService;
    }

    public List<AnswerResponse> getAllAnswers() {
        return answerRepository.findAll().stream().map(this::mapToAnswerResponse).toList();
    }

    private AnswerResponse mapToAnswerResponse(Answer answer) {
        return AnswerResponse.builder()
                .id(answer.getId())
                .answer(answer.getAnswer())
                .user(answer.getUser())
                .build();
    }

    public void createAnswer(AnswerRequest answerRequest) {
        User user = userService.getUserObject(answerRequest.getUserId());

        Answer answer = Answer.builder()
                .answer(answerRequest.getAnswer())
                .user(user)
                .build();
        answerRepository.save(answer);
        log.info("Created answer: " + answer.getAnswer() + " to user: " + user.getEmail());
    }

    public void deleteAllAnswers() {
        answerRepository.deleteAll();
    }
}
