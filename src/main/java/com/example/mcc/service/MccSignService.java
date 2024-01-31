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

    public boolean isDuplicate(String number, String password){

        boolean isExist = memberRepository.existsByMemberNumber(number);

        //같은 학번이 존재하지 않는다면
        if(!isExist){
            memberRepository.save(new member(number, password));
            return false;
        }

        // 같은 학번이 존재한다면
        return true;
    }
}
