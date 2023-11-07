package com.erikhillard.sheepgame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_answer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }) // No remove, so we keep the User even if the Answer is deleted
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
