package com.example.mcc.controller;

import com.example.mcc.Dto.RegistVoteDto;
import com.example.mcc.entity.Vote;
import com.example.mcc.response.voteResponse;
import com.example.mcc.service.MccVoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.example.mcc.response.Message.*;

@Api(tags = "투표 기능 Api")
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
    @ApiOperation(value = "투표 글 작성 Api" , notes = "투표 제목 , 평가항목 , 팀")
    @Parameters({
            @Parameter(name = "registVote" , description = "List Of Vote")
    })

    @PostMapping("/vote/regist")
    public ResponseEntity<voteResponse> voteregist(@RequestBody RegistVoteDto registVote){
        log.info("투표 글 작성 controller 시작");

        /*
        * 투표제목 , 평가항목 , 평가 받을 팀 DB에 저정하기
        * */
        String voteName = registVote.getVoteName();
        List<String> evaluationList = registVote.getEvaluationName();
        List<String> teamNameList = registVote.getTeamName();

        String message = mccVoteService.saveVote(voteName, evaluationList, teamNameList);


        if(message.equals(VOTE_FAIL_SAVE)){
            //투표저장을 위한 투표 이름 , 평가항목 , 팀이름이 하나라도 안적혀 있다면 실패.
            voteResponse.setMessage(VOTE_FAIL_SAVE);
            return ResponseEntity.badRequest().body(voteResponse);
        }

        voteResponse.setMessage(VOTE_SUCCESS_SAVE);
        return ResponseEntity.ok().body(voteResponse);
    }

    //투표 목록 불러오기 API
    @ApiOperation(value = "투표 리스트 불러오기 Api")
    @GetMapping("/list")
    public ResponseEntity<Map<Long, Vote>> votelist(){
        log.info("list controller");
        Map<Long, Vote> voteMap = mccVoteService.getVoteList();

        return ResponseEntity.ok().body(voteMap);
    }

    // 투표를 하기 위해 눌렀을때 투표 양식 데이터 가져오기
    @ApiOperation(value = "투표 양식 Api" , notes = "투표 번호")
    @GetMapping("/vote/form")
    public ResponseEntity<Vote> voteform(@RequestBody Long voteNumber){
        /*
         * 투표 이름에 따른 해당 하는 투표에 양식 데이터 가져오기
         */
        Vote vote = mccVoteService.searchVote(voteNumber);


//        //만약 투표를 한 회원이라면 안된다고 알려주기
//        if(mccVoteService.isReVote(memberNumber,number))
//            return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(vote);

    }
    //투표 점수 저장하기
    @PostMapping("/vote/save")
    public voteResponse votesave(@RequestParam("number")Long number
            ,@RequestBody RegistVoteDto voteBoard
            , HttpServletRequest request){

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String memberNumber = (String) authentication.getPrincipal();
//
//        //데이터베이스에 저장되어 있는 투표 가져오기
//        //데이터베이스에 저장되어 있는 동아리원 가져오기
//        //투표와 동아리원을 연결 투표참가(participant) 테이블에 저장
//
//        List<Team> inputTeam = voteBoard.getTeam();
//        mccVoteService.enter(memberNumber, number,inputTeam);
//        voteResponse.setMessage(VOTE_SUCCESS_SCORE_SAVE);
//        voteResponse.setVotes(null);
//        return voteResponse;
        return null;
    }

    @GetMapping("/vote/result")
    public voteResponse voteresult(){
        return null;
    }
}
