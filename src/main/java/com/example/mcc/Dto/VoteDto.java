package com.example.mcc.Dto;

import com.example.mcc.entity.Team;
import com.example.mcc.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VoteDto {
    private Vote vote;
    private List<Team> team;
}
