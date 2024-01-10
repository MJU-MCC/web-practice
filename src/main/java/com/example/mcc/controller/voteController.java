package com.example.mcc.controller;

import com.example.mcc.Dto.VoteDto;
import com.example.mcc.entity.Evaluation;
import com.example.mcc.entity.Team;
import com.example.mcc.entity.Vote;
import com.example.mcc.response.voteResponse;
import com.example.mcc.service.MccVoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.mcc.response.Message.VOTE_SUCCESS;
import static com.example.mcc.response.Message.VOTE_SUCCESS_SCORE_SAVE;
import static com.example.mcc.response.voteResponse.success;

@RestController
@RequestMapping("/mcc")
@Slf4j
public class voteController {

    private final MccVoteService mccVoteService;

    public voteController(MccVoteService mccVoteService) {
        this.mccVoteService = mccVoteService;
    }

    // 투표 글 작성하기
    @PostMapping("/vote/regist")
    public voteResponse voteregist(@RequestBody VoteDto voteDto){

        String title = voteDto.getVote().getVoteName();
        List<Evaluation> inputEvals = voteDto.getEvaluation();
        List<Team> inputTeams = voteDto.getTeam();

        //투표 제목 등록
        Vote vote = new Vote();
        vote.setVoteName(title);
        vote.setVoteDate(LocalDate.now());

        ArrayList<Evaluation> evals = new ArrayList<>();
        ArrayList<Team> teams = new ArrayList<>();
        for(Team ts : inputTeams){
            Team team = new Team();
            team.setTeamName(ts.getTeamName());

            teams.add(team);
        }
        //투표 항목 등록
        for(Evaluation evaluations :inputEvals){

            Evaluation evaluation = new Evaluation();
            evaluation.setEvaluationName(evaluations.getEvaluationName());
            evaluation.setTeamList(teams);

            evals.add(evaluation);
        }
        vote.setEvaluationList(evals);

        String str = mccVoteService.saveVote(vote);

        return success(str);
    }

    //투표 목록 불러오기 API
    @GetMapping("/vote/list")
    public voteResponse votelist(){
        List<String> titleList = mccVoteService.showList();

        return success(VOTE_SUCCESS,titleList);
    }

    // 투표 글 저장한 평가목록 , 팀 데이터 불러오기
    @GetMapping("/vote/form/{number}")
    public Vote voteform(@PathVariable Long number){
        log.info("매개변수로 받은 number = {}", number);
        Vote findVote = mccVoteService.searchVote(number);
        return findVote;
    }

    @PostMapping("/vote/save")
    public voteResponse votesave(@RequestBody VoteDto voteBoard){





        return success(VOTE_SUCCESS_SCORE_SAVE);
    }
    @GetMapping("/vote/result")
    public voteResponse voteresult(){
        return null;
    }
}
