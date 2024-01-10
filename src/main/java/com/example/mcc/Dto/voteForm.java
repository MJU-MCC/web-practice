package com.example.mcc.Dto;

import com.example.mcc.entity.Evaluation;
import com.example.mcc.entity.Team;
import com.example.mcc.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class voteForm {

    private List<Team> teams;
    private List<Evaluation> evaluations;
}
