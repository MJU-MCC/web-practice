package com.example.mcc.repository;

import com.example.mcc.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByVote_VoteId(Long id);

}
