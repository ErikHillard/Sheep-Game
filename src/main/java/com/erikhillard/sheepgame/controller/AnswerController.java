package com.erikhillard.sheepgame.controller;

import com.erikhillard.sheepgame.dto.AnswerRequest;
import com.erikhillard.sheepgame.dto.AnswerResponse;
import com.erikhillard.sheepgame.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnswerResponse> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnswer(@RequestBody AnswerRequest answerRequest) {
        answerService.createAnswer(answerRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllAnswers() {
        answerService.deleteAllAnswers();
    }
}
