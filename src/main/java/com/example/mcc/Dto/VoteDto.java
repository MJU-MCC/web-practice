package com.example.mcc.Dto;

import com.example.mcc.entity.Team;
import com.example.mcc.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private Vote vote;
    private List<Team> team;
}
