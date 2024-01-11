package com.example.mcc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue
    private Long voteId;

    //투표 제목
    private String voteName;

    //평가 항목
    @ElementCollection
    private List<String> Evaluation;

    //투표 날짜
    private LocalDate voteDate;


    @OneToMany(mappedBy = "vote")
    private List<participant> participantList = new ArrayList<>();

    @OneToMany(mappedBy = "vote")
    @JsonIgnore
    private List<Team> teamList = new ArrayList<>();
}
