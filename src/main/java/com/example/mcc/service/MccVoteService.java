package com.example.mcc.service;

import com.example.mcc.Dto.VoteForm;
import com.example.mcc.entity.Vote;
import com.example.mcc.entity.Member;
import com.example.mcc.entity.participant;
import com.example.mcc.repository.MemberRepository;
import com.example.mcc.repository.ParticipantRepository;
import com.example.mcc.repository.TeamRepository;
import com.example.mcc.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.mcc.response.Message.VOTE_SUCCESS_SAVE;

@Service
@Slf4j
@AllArgsConstructor
public class MccVoteService {

    private VoteRepository voteRepository;
    private MemberRepository memberRepository;
    private ParticipantRepository participantRepository;
    private TeamRepository teamRepository;


    public List<String> showList() {
        //저장소에서 저장된 투표들을 가져오기
        List<Vote> votes = voteRepository.findAll();

        //투표 제목들을 담기 위한 리스트
        ArrayList<String> list = new ArrayList<>();

        //꺼낸 투표들에서 제목들만 꺼내서 제목리스트에 담기
        Iterator<Vote> voteList = votes.iterator();
        while(voteList.hasNext()){
            String title = voteList.next().getVoteName();
            list.add(title);
        }
        //담은 제목 리스트를 반환
        return list;
    }
    //투표 저장하기
    public String saveVote(Vote vote,List<Team> teams) {
        voteRepository.save(vote);
        teamRepository.saveAll(teams);
        return VOTE_SUCCESS_SAVE;
    }
    //투표 찾기
    public VoteForm searchVote(Long number) {
        Vote findVote = voteRepository.findByvoteId(number);

        //프론트에게 전달 해야하는 정보들 -> 투표 제목 , 평가 항목들 , 팀
        List<Team> findTeam = teamRepository.findByVote_VoteId(number);//팀
        ArrayList<String> teamNames = new ArrayList<>();

        for(Team t:findTeam){
            teamNames.add(t.getTeamName());
        }
        VoteForm sendResult = new VoteForm(findVote.getVoteName()
                                            , findVote.getEvaluation()
                                            , teamNames);


        return sendResult;

    }

    public boolean isReVote(String number,Long voteNumber){

        //회원 정보를 바탕으로 회원 테이블에 PK값 가져오기
        Member findMember = memberRepository.findByMemberNumber(number);
        Long memberId = findMember.getMemberId();

        //투표 정보를 바탕으로 투표 테이블에 PK값 가져오기
        Vote findVote = voteRepository.findByvoteId(voteNumber);
        Long voteNum = findVote.getVoteId();

        participant savedPar = participantRepository.findByMember_MemberIdAndVote_VoteId(memberId, voteNum);
        if(savedPar != null) {
            Boolean isDone = savedPar.getIsCheck();
            return true;
        }

        return false;
    }

    public void enter(String savedMembernumber,Long savedVoteNumber,List<Team> teams){
        //저장소에서 투표 요청을 한 유저 꺼내기
        Member findMember = memberRepository.findByMemberNumber(savedMembernumber);
        log.info("findMember = {}",findMember);

        //저장소에서 투표 요청을 한 투표 꺼내기
        Vote findVote = voteRepository.findByvoteId(savedVoteNumber);
        log.info("findVote = {}",findVote);
        //저장소에서 투표에 맞는 팀들을 꺼내기
        List<Team> findTeams = teamRepository.findByVote_VoteId(savedVoteNumber);

        for(int i=0; i<teams.size(); i++){
            findTeams.get(i).setVoteScore(teams.get(i).getVoteScore());
        }
        for(Team t: findTeams)
            System.out.println("저장된 팀들의 점수는 이렇게 됩니다 = "+t.getVoteScore());

        participant enter = new participant();
        enter.setVote(findVote);
        enter.setMember(findMember);
        enter.setIsCheck(true);
        enter.setCreatedAt(LocalDate.now());
        ArrayList<participant> partList = new ArrayList<>();
        partList.add(enter);

        participantRepository.save(enter);
        findMember.setParticipantList(partList);
        memberRepository.save(findMember);

    }
}
