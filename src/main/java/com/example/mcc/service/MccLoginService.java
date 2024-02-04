package com.example.mcc.service;

import com.example.mcc.entity.Member;
import com.example.mcc.repository.MemberRepository;
import com.example.mcc.repository.TokenRepository;
import com.example.mcc.security.tokenUtil.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MccLoginService {

    @Value("${jwt.secret.key}")
    private String secretKey;
    private final Long expiredTime = 1000 * 60 * 60L;
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;

    public MccLoginService( MemberRepository memberRepository, TokenRepository tokenRepository) {
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
    }

    public String login(String memberNumber, String memberPassword) {

        Member findMember = memberRepository.findByMemberNumber(memberNumber);
        String role = findMember.getRole();
        if(findMember.getMemberPassword().equals(memberPassword)) {
            String token = JwtTokenUtil.createToken(memberNumber, secretKey, 60 * 10 * 1000L);

//            String refreshToken = tokenList.get(1);
//
//            Token saveToken = new Token();
//            saveToken.setRefreshToken(refreshToken);
//            saveToken.setMember(findMember);
//            tokenRepository.save(saveToken);

            return token;
        }

        return null;
    }


}

