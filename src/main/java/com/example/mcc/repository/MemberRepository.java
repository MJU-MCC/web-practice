package com.example.mcc.repository;

import com.example.mcc.Dto.memberDto;
import com.example.mcc.entity.member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<member,Long> {
    Optional<memberDto> findByMemberNumber(String number);
}
