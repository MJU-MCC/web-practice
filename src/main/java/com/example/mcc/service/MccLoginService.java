package com.example.mcc.service;

import com.example.mcc.Dto.memberDto;
import com.example.mcc.entity.member;
import com.example.mcc.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MccLoginService {

    private final MemberRepository memberRepository;

    public MccLoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public member login(String number, String password) {

        return memberRepository.findByMemberNumber(number);
        }
}

