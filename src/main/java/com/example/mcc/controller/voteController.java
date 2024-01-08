package com.example.mcc.controller;

import com.example.mcc.Dto.VoteDto;
import com.example.mcc.entity.Evaluation;
import com.example.mcc.entity.Team;
import com.example.mcc.entity.Vote;
import com.example.mcc.response.voteResponse;
import com.example.mcc.service.MccVoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.mcc.response.Message.VOTE_SUCCESS;
import static com.example.mcc.response.voteResponse.success;

@RestController
@RequestMapping("/mcc")
@Slf4j
public class voteController {

    private final MccVoteService mccVoteService;

    public voteController(MccVoteService mccVoteService) {
        this.mccVoteService = mccVoteService;
    }

    //투표 목록 불러오기 API
    @GetMapping("/vote/list")
    public voteResponse votelist(){
        List<Vote> result = mccVoteService.showList();

        return success(VOTE_SUCCESS,result);
    }

    @GetMapping("/vote/form")
    public voteResponse voteform(){
        return null;
    }


    @PostMapping("/vote/regist")
    public voteResponse voteregist(@RequestBody VoteDto voteDto){

        String title = voteDto.getVoteName();
        List<Evaluation> evaluationList = voteDto.getEvaluation();
        List<Team> teamList = voteDto.getTeam();

        //투표 제목 등록
        Vote vote = new Vote();
        vote.setVoteName(title);

        ArrayList<Evaluation> evals = new ArrayList<>();
        ArrayList<Team> teams = new ArrayList<>();
        //투표 항목 등록
        for(Evaluation evaluations :evaluationList){
            Evaluation evaluation = new Evaluation();
            evaluation.setEvaluationName(evaluations.getEvaluationName());

            evals.add(evaluation);
        }
        vote.setEvaluationList(evals);

        //투표 팀 등록
        for(Team ts : teamList){
            Team team = new Team();
            team.setTeamName(ts.getTeamName());

            teams.add(team);
        }
        vote.setTeamList(teams);


        String str = mccVoteService.saveVote(vote);


        return success(str);
    }
    @PostMapping("/vote/save")
    public voteResponse votesave(){
        return null;
    }
    @GetMapping("/vote/result")
    public voteResponse voteresult(){
        return null;
    }
}
