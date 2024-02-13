package com.example.mcc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    //투표번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    //투표 제목
    private String voteName;


    @OneToMany(mappedBy = "vote")
    private List<participant> participantList = new ArrayList<>();

    @OneToMany(mappedBy = "vote")
    private List<Evaluation> evaluationsList = new ArrayList<>();

    @OneToMany(mappedBy = "vote")
    private List<Team> teamList = new ArrayList<>();

}
