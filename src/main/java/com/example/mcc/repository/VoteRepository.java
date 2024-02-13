package com.example.mcc.repository;

import com.example.mcc.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    public Vote findByvoteId(Long voteId);
    public Vote findVoteByVoteName(String voteName);
}
