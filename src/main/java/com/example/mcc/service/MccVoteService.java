package com.example.mcc.service;

import com.example.mcc.entity.Vote;
import com.example.mcc.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.mcc.response.Message.VOTE_SUCCESS_SAVE;

@Service
@Slf4j
public class MccVoteService {

    private VoteRepository voteRepository;

    public MccVoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

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
        Vote saved = voteRepository.save(vote);

        return VOTE_SUCCESS_SAVE;
    }

    public Vote searchVote(Long number) {
        Vote findVote = voteRepository.findByvoteId(number);
        log.info("찾은 투표 게시물 = {}",findVote.getVoteName());

        return findVote;
    }
}
