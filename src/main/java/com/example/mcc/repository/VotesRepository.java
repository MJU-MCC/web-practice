package com.example.mcc.repository;

import com.example.mcc.entity.memberVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotesRepository extends JpaRepository<memberVote,Long> {
}
