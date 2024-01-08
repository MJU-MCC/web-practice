package com.example.mcc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    //멤버와 투표는 N:M관계 한개의 투표에 여러명의 멤버 , 한명의 멤버는 여러 두표 가능

    @Id @GeneratedValue
    private Long voteId;

    private LocalTime voteDate;
    private String voteName;

    @OneToMany(mappedBy = "vote")
    private List<memberVote> memberVoteList = new ArrayList<>();


    @OneToMany(mappedBy = "vote")
    private List<Team> teamList = new ArrayList<>();

    @OneToMany(mappedBy = "vote")
    private List<Evaluation> evaluationList = new ArrayList<>();

}
