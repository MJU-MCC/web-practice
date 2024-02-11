package com.example.mcc.service;

import com.example.mcc.entity.Vote;
import com.example.mcc.entity.Member;
import com.example.mcc.entity.participant;
import com.example.mcc.repository.MemberRepository;
import com.example.mcc.repository.ParticipantRepository;
import com.example.mcc.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.example.mcc.response.Message.VOTE_FAIL_SAVE;
import static com.example.mcc.response.Message.VOTE_SUCCESS_SAVE;

@Service
@Slf4j
@AllArgsConstructor
public class MccVoteService {

    private VoteRepository voteRepository;
    private MemberRepository memberRepository;
    private ParticipantRepository participantRepository;

    //투표 리스트 불러오기
    public Map<Long, Vote> getVoteList() {
        Map<Long, Vote> votes = new HashMap<>();

        Iterator<Vote> findVotes = voteRepository.findAll()
                                    .stream()
                                    .iterator();

        while(!findVotes.hasNext()){
            Vote nextVote = findVotes.next();
            Long number = nextVote.getVoteId();

            votes.put(number,nextVote);

            log.info("꺼낸 vote의 넘버 = {} , {}",number , nextVote.getVoteName());
        }
        return votes;
    }

    //투표 저장하기
    public String saveVote(Vote saveVote) {
        if(saveVote.getVoteName().isEmpty()){
            return VOTE_FAIL_SAVE;
        }
        if(saveVote.getTeamName().isEmpty()){
            return VOTE_FAIL_SAVE;
        }
        if(saveVote.getEvaluation().isEmpty()){
            return VOTE_FAIL_SAVE;
        }

        voteRepository.save(saveVote);

        return VOTE_SUCCESS_SAVE;
    }

    //투표 찾기
    public Vote searchVote(Long number) {
        Vote findVote = voteRepository.findByvoteId(number);
        log.info("findVote = {}", findVote);
        //프론트에게 전달 해야하는 정보들 -> 투표 제목 , 평가 항목들 , 팀

        return findVote;

    }
//
//    public boolean isReVote(String number,Long voteNumber){
//
//        //회원 정보를 바탕으로 회원 테이블에 PK값 가져오기
//        Member findMember = memberRepository.findByMemberNumber(number);
//        Long memberId = findMember.getMemberId();
//
//        //투표 정보를 바탕으로 투표 테이블에 PK값 가져오기
//        Vote findVote = voteRepository.findByvoteId(voteNumber);
//        Long voteNum = findVote.getVoteId();
//
//        participant savedPar = participantRepository.findByMember_MemberIdAndVote_VoteId(memberId, voteNum);
//        if(savedPar != null) {
//            Boolean isDone = savedPar.getIsCheck();
//            return true;
//        }
//
//        return false;
//    }
//
//    public void enter(String savedMembernumber,Long savedVoteNumber,List<Team> teams){
//        //저장소에서 투표 요청을 한 유저 꺼내기
//        Member findMember = memberRepository.findByMemberNumber(savedMembernumber);
//        log.info("findMember = {}",findMember);
//
//        //저장소에서 투표 요청을 한 투표 꺼내기
//        Vote findVote = voteRepository.findByvoteId(savedVoteNumber);
//        log.info("findVote = {}",findVote);
//        //저장소에서 투표에 맞는 팀들을 꺼내기
//        List<Team> findTeams = teamRepository.findByVote_VoteId(savedVoteNumber);
//
//        for(int i=0; i<teams.size(); i++){
//            findTeams.get(i).setVoteScore(teams.get(i).getVoteScore());
//        }
//        for(Team t: findTeams)
//            System.out.println("저장된 팀들의 점수는 이렇게 됩니다 = "+t.getVoteScore());
//
//        participant enter = new participant();
//        enter.setVote(findVote);
//        enter.setMember(findMember);
//        enter.setIsCheck(true);
//        enter.setCreatedAt(LocalDate.now());
//        ArrayList<participant> partList = new ArrayList<>();
//        partList.add(enter);
//
//        participantRepository.save(enter);
//        findMember.setParticipantList(partList);
//        memberRepository.save(findMember);
//
//    }
}
