package com.example.mcc.service;

import com.example.mcc.entity.member;
import com.example.mcc.repository.MemberRepository;
import com.example.mcc.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MccLoginService {

    @Value("${secret.jwt.key}")
    private String secretKey;
    private final Long expiredTime = 1000 * 60 * 60L;
    private final MemberRepository memberRepository;

    public MccLoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String login(String memberNumber, String memberPassword) {

        member findMember = memberRepository.findByMemberNumber(memberNumber);

        if(findMember.getMemberPassword().equals(memberPassword))
            return JwtUtil.createJwt(memberNumber,secretKey,expiredTime);

        return null;
    }


}

