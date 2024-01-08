package com.example.mcc.repository;

import com.example.mcc.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByvoteId(Long voteId);
}
