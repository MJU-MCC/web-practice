package com.example.mcc.repository;

import com.example.mcc.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    public List<Evaluation> findAllByVoteVoteId(Long voteId);
}
