package com.example.mcc.repository;

import com.example.mcc.entity.Evaluation;
import com.example.mcc.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
    public List<Team> findAllByVoteVoteId(Long voteId);
}
