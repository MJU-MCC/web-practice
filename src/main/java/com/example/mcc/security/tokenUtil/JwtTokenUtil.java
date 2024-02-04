package com.example.mcc.security.tokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class JwtTokenUtil {

    //jwt 토큰 만드는 메서드
    public static String createToken(String memberNumber , String key , long expireTImeMs){

        Claims claims = Jwts.claims();
        claims.put("memberNumber",memberNumber);
//        log.info("create메서드에서 key는 = {}", key);

        byte[] bytes = key.getBytes();
        byte[] encodeBytes = Base64.getEncoder().encode(bytes);
//        log.info("create메서드에서 encodeBytes는 = {}", encodeBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTImeMs))
                .signWith(SignatureAlgorithm.HS256, encodeBytes)
                .compact();
    }

    //Claims에서 memberNumber 꺼내기
    public static String getMemberNumber(String token , String secretKey){
        return  getClaims(token,secretKey).get("memberNumber").toString();
    }

    //Token이 만료시간이 지나지 않았는지 체크
    public static boolean isExpired(String token , String secretKey){

        Date expiration = getClaims(token, secretKey)  //토큰의 claims를 꺼내서
                .getExpiration();                       //claims에 있는 exp를 꺼낸다.
                                                        //꺼낸 뒤 expiration 변수에 넣어서 현재시간과 비교하기
        return expiration.before(new Date());
    }

    //토큰을 받아서 claim부분만 추출하기
    private static Claims getClaims(String token , String secretKey){
        byte[] bytes = secretKey.getBytes();
        byte[] encodeBytes = Base64.getEncoder().encode(bytes);
//
//        log.info("입력받은 secretKey는 = {}",secretKey);
//
//        log.info("입력받은 secretKeyBytes는 = {}", encodeBytes);
        return Jwts.parser()
                .setSigningKey(encodeBytes)
                .parseClaimsJws(token)
                .getBody();
    }
}
