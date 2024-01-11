package com.example.mcc.repository;

import com.example.mcc.entity.participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<participant,Long> {
    public participant findByMember_MemberIdAndVote_VoteId(Long memberid,Long voteId);
}
