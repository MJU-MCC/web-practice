package com.example.mcc.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistVoteDto {

    //투표 제목
    private String VoteName;

    //평가 : 평가항목
    private String EvaluationName;

    //평가 : 평가 받을 팀
    private String TeamName;
}
