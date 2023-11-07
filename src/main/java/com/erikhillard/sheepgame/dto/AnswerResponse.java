package com.erikhillard.sheepgame.dto;

import com.erikhillard.sheepgame.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {
    private Long id;
    private String answer;
    private User user;
}
