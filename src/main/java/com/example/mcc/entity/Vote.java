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
@Builder
public class Vote {

    //투표번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //투표 제목
    private String voteName;

    //평가 항목
    private String evaluation;

    //평가 받을 팀 이름
    private String teamName;

    //점수
    private Integer score;


    @OneToMany(mappedBy = "vote")
    private List<participant> participantList = new ArrayList<>();

}
