package com.example.mcc.service;

import com.example.mcc.Dto.voteForm;
import com.example.mcc.entity.Evaluation;
import com.example.mcc.entity.Team;
import com.example.mcc.entity.Vote;
import com.example.mcc.entity.memberVote;
import com.example.mcc.repository.EvaluationRepository;
import com.example.mcc.repository.TeamRepository;
import com.example.mcc.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.mcc.response.Message.VOTE_SUCCESS_SAVE;

@Service
@Slf4j
@AllArgsConstructor
public class MccVoteService {

    private VoteRepository voteRepository;
    private EvaluationRepository evaluationRepository;
    private TeamRepository teamRepository;


    public List<String> showList() {
        List<Vote> votes = voteRepository.findAll();

        ArrayList<String> list = new ArrayList<>();
        Iterator<Vote> voteList = votes.iterator();
        while(voteList.hasNext()){
            String title = voteList.next().getVoteName();
            list.add(title);
        }
        return list;
    }

    public String saveVote(Vote vote) {
        List<Evaluation> savedEvaluationList = vote.getEvaluationList();
        List<Team> savedTeamList = savedEvaluationList.get(0).getTeamList();

        voteRepository.save(vote);
        for(Evaluation saved:savedEvaluationList){
            evaluationRepository.save(saved);
        }
        for(Team saved : savedTeamList){
            teamRepository.save(saved);
        }


        return VOTE_SUCCESS_SAVE;
    }

    public voteForm searchVote(Long number) {
        Vote findVote = voteRepository.findByvoteId(number);
        log.info("찾은 투표 게시물 = {}",findVote.getVoteName());

        List<Evaluation> evaluationList = findVote.getEvaluationList();
        List<Team> teamList = evaluationList.get(0).getTeamList();
        voteForm voteForm = new voteForm(teamList, evaluationList);
        return voteForm;
    }
}
