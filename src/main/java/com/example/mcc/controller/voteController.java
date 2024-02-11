package com.example.mcc.controller;

import com.example.mcc.Dto.RegistVoteDto;
import com.example.mcc.entity.Vote;
import com.example.mcc.response.voteResponse;
import com.example.mcc.service.MccVoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.example.mcc.response.Message.*;

@RestController
@RequestMapping("/mcc")
@Slf4j
public class voteController {

    private  MccVoteService mccVoteService;
    private  voteResponse voteResponse;

    public voteController(MccVoteService mccVoteService, com.example.mcc.response.voteResponse voteResponse) {
        this.mccVoteService = mccVoteService;
        this.voteResponse = voteResponse;
    }

    // 투표 글 작성하기
    @PostMapping("/vote/regist")
    public ResponseEntity<voteResponse> voteregist(@RequestBody RegistVoteDto registVote){
        log.info("투표 글 작성 controller 시작");

        /*
        * 투표제목 , 평가항목 , 평가 받을 팀 DB에 저정하기
        * */
        String voteName = registVote.getVoteName();
        String evaluationName = registVote.getEvaluationName();
        String teamName = registVote.getTeamName();

        Vote saveVote = Vote.builder()
                .voteName(voteName)
                .evaluation(evaluationName)
                .teamName(teamName)
                .build();

        log.info("Builder로 만들어진 Vote = {}",saveVote);

        String message = mccVoteService.saveVote(saveVote);

        if(message.equals(VOTE_FAIL_SAVE)){
            //투표저장을 위한 투표 이름 , 평가항목 , 팀이름이 하나라도 안적혀 있다면 실패.
            voteResponse.setMessage(VOTE_FAIL_SAVE);
            voteResponse.setVotes(saveVote);
            return ResponseEntity.badRequest().body(voteResponse);
        }

        voteResponse.setMessage(VOTE_SUCCESS_SAVE);
        voteResponse.setVotes(saveVote);
        return ResponseEntity.ok().body(voteResponse);
    }

    //투표 목록 불러오기 API
    @GetMapping("/list")
    public ResponseEntity<Map<String, Vote>> votelist(){
        Map<String, Vote> voteMap = mccVoteService.getVoteList();

        return ResponseEntity.ok().body(voteMap);
    }

    // 투표 글 저장한 평가목록 , 팀 데이터 불러오기
    @GetMapping("/vote/form/{number}")
    public ResponseEntity<VoteForm> voteform(@PathVariable Long number
            , HttpServletRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberNumber = (String) authentication.getPrincipal();

        //만약 투표를 한 회원이라면 안된다고 알려주기
        if(mccVoteService.isReVote(memberNumber,number))
            return ResponseEntity.badRequest().body(null);

        //매개변수로 받은 투표 해당 번호를 서비스 계층으로 전달하기
        return ResponseEntity.ok(mccVoteService.searchVote(number));
    }
    //투표 점수 저장하기
    @PostMapping("/vote/save")
    public voteResponse votesave(@RequestParam("number")Long number
            ,@RequestBody RegistVoteDto voteBoard
            , HttpServletRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberNumber = (String) authentication.getPrincipal();

        //데이터베이스에 저장되어 있는 투표 가져오기
        //데이터베이스에 저장되어 있는 동아리원 가져오기
        //투표와 동아리원을 연결 투표참가(participant) 테이블에 저장

        List<Team> inputTeam = voteBoard.getTeam();
        mccVoteService.enter(memberNumber, number,inputTeam);
        voteResponse.setMessage(VOTE_SUCCESS_SCORE_SAVE);
        voteResponse.setVotes(null);
        return voteResponse;
    }

    @GetMapping("/vote/result")
    public voteResponse voteresult(){
        return null;
    }
}
