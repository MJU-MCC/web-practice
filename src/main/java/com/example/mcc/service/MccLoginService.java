package com.example.mcc.service;

import com.example.mcc.Dto.memberDto;
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

    public Optional<memberDto> login(String number, String password){
        Optional<memberDto> findUser = memberRepository.findByMemberNumber(number);

        if(findUser.isPresent()){
            //저장소에 유저가 존재한다면
            memberDto successLoginMemberDto = findUser.get();
            log.info("findUser 객체의 비밀번호 = {}", findUser.get().getMemberPassword());

            if(successLoginMemberDto.getMemberPassword().equals(password)){
                //회원이 입력한 비밀번호와 저장소에 있는 비밀번호가 같은지 확인하기
                return findUser;
            }else{
                //비밀번호가 다르다면
                return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
}
}

