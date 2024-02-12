package com.example.mcc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    private String evaluationName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @OneToMany(mappedBy = "evaluation")
    private List<Candidate> candidateList = new ArrayList<>();
}
