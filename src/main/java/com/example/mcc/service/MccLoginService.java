package com.example.mcc.service;

import com.example.mcc.entity.Member;
import com.example.mcc.repository.MemberRepository;
import com.example.mcc.repository.TokenRepository;
import com.example.mcc.security.tokenUtil.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class MccLoginService {

    @Value("${jwt.secret.key}")
    private String secretKey;
    private final Long expiredTime = 1000 * 60 * 60L;

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final RedisTemplate<String ,String> redisTemplate;

    public MccLoginService(MemberRepository memberRepository, TokenRepository tokenRepository, RedisTemplate<String, String> redisTemplate) {
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
        this.redisTemplate = redisTemplate;
    }

    //@Resource(name = "redisTemplate") 어노테이션을 이용해서 RedisTEmplate를 거치지않고도 Operations 주입받기 가능
    //name 에는 빈이름을 넣어주면 됩니다.
    public List<String> login(String memberNumber, String memberPassword) {

        Member findMember = memberRepository.findByMemberNumber(memberNumber);
        String role = findMember.getRole();
        if(findMember.getMemberPassword().equals(memberPassword)) {

            String accessToken = JwtTokenUtil.accessTokenCreate(memberNumber, secretKey, 60 * 30* 1000L);//30분 = 1000 * 60 * 30
            String refreshToken = JwtTokenUtil.refreshTokenCreate(secretKey, 60 * 60 * 1000 * 24L); //1일 = 24 * 60 *60 *1000

            ArrayList<String> tokenList = new ArrayList<>();
            tokenList.add("Bearer "+accessToken);
            tokenList.add(refreshToken);

            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();//한 쌍 박스를 redis에서 꺼내기
            valueOps.set(memberNumber , refreshToken);


            return tokenList;
        }

        return null;
    }


}

