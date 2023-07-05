package com.haroffcode.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity()
@Table(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String questionTitle;
    @Column(nullable = false)
    private String optionA;
    @Column(nullable = false)
    private String optionB;
    @Column(nullable = false)
    private String answer;
    @Column(nullable = false)
    private String category;
    private String difficulty;
}
