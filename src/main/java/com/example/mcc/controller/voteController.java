package com.example.mcc.controller;

import com.example.mcc.Dto.VoteDto;
import com.example.mcc.Dto.VoteForm;
import com.example.mcc.entity.Team;
import com.example.mcc.entity.Vote;
import com.example.mcc.entity.Member;
import com.example.mcc.response.voteResponse;
import com.example.mcc.security.tokenUtil.JwtTokenUtil;
import com.example.mcc.service.MccVoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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
        log.info("regist controller 진입");
        Vote inputVote = voteDto.getVote();
        //평가 될 팀
        List<Team> inputTeams = voteDto.getTeam();


        Vote trySaveVote = new Vote();

        //투표 제목
        String title = inputVote.getVoteName();
        //평가 항목
        List<String> evals = inputVote.getEvaluation();

        //저장할 투표 테이블 객체 세팅 완료
        trySaveVote.setVoteName(title);
        trySaveVote.setVoteDate(LocalDate.now());
        trySaveVote.setEvaluation(evals);
        trySaveVote.setTeamList(inputTeams);


        for(Team ts : inputTeams){
            ts.setVote(trySaveVote);
        }
        //저장소에 저장
        String message = mccVoteService.saveVote(trySaveVote , inputTeams);
        return success(message);
    }

    //투표 목록 불러오기 API
    @GetMapping("/list")
    public voteResponse votelist(){

        //투표 제목들을 담은 리스트를 반환 받음
        List<String> titleList = mccVoteService.showList();

        return success(VOTE_SUCCESS,titleList);
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
            ,@RequestBody VoteDto voteBoard
            , HttpServletRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberNumber = (String) authentication.getPrincipal();

        //데이터베이스에 저장되어 있는 투표 가져오기
        //데이터베이스에 저장되어 있는 동아리원 가져오기
        //투표와 동아리원을 연결 투표참가(participant) 테이블에 저장

        List<Team> inputTeam = voteBoard.getTeam();
        mccVoteService.enter(memberNumber, number,inputTeam);
        return success(VOTE_SUCCESS_SCORE_SAVE);
    }

    @GetMapping("/vote/result")
    public voteResponse voteresult(){
        return null;
    }
}
