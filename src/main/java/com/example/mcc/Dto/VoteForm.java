package com.example.mcc.Dto;

import com.example.mcc.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VoteForm {

    private String title;
    private List<String> Evaluations;
    private List<String> teams;
}
