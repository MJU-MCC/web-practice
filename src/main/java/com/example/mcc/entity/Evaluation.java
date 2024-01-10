package com.example.mcc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    //한개의 평가항목이 여러 투표글에 들어갈수 있다. 한개의 투표글은 여러개의 평가항목을 가질수있다.

    @Id @GeneratedValue
    private Long evaluationId;

    private String evaluationName;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @OneToMany(mappedBy = "evaluation")
    private List<Team> teamList= new ArrayList<>();
}
