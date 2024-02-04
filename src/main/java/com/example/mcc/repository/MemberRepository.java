package com.example.mcc.repository;

import com.example.mcc.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMemberNumber(String number);
    boolean existsByMemberNumber(String number);
}
