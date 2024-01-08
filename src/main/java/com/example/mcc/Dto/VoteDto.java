package com.example.mcc.Dto;

import com.example.mcc.entity.Evaluation;
import com.example.mcc.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VoteDto {
    private String voteName;
    private List<Evaluation> evaluation;
    private List<Team> team;
}
