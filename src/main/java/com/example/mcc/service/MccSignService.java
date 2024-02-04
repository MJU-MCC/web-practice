package com.example.mcc.service;

import com.example.mcc.entity.Member;
import com.example.mcc.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MccSignService {

    private final MemberRepository memberRepository;

    public MccSignService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean isDuplicateAndSignUp(String number, String password, String role){

        boolean isExist = memberRepository.existsByMemberNumber(number);

        //같은 학번이 존재하지 않는다면
        if(!isExist){
            memberRepository.save(new Member(number, password,role));
            return false;
        }

        // 같은 학번이 존재한다면
        return true;
    }
}
