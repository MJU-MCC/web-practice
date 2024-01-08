package com.example.mcc.service;

import com.example.mcc.entity.Vote;
import com.example.mcc.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mcc.response.Message.VOTE_SUCCESS_SAVE;

@Service
@Slf4j
public class MccVoteService {

    private VoteRepository voteRepository;

    public MccVoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> showList() {
        List<Vote> votes = voteRepository.findAll();
        return votes;
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
