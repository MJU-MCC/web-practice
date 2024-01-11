package com.example.mcc.service;

import com.example.mcc.entity.member;
import com.example.mcc.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.mcc.response.Message.SIGN_SUCCESS;

@Service
@Slf4j
public class MccSignService {

    private final MemberRepository memberRepository;

    public MccSignService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String sign(String number, String password){
        member newMember = new member(number,password);

        log.info("newMember에서 저장되는 객체는 = {} , {}",newMember.getMemberNumber(), newMember.getMemberPassword() );

        memberRepository.save(newMember);
        return SIGN_SUCCESS;
    }
}
