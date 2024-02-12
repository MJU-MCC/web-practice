package com.example.mcc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateId;

    private Long candidateScore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;
}
