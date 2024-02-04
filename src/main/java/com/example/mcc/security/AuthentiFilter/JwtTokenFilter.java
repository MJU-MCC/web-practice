package com.example.mcc.security.AuthentiFilter;


import com.example.mcc.security.tokenUtil.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenFilter  extends OncePerRequestFilter {

    @Value("${jwt.secret.key}")
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*
        * 요청에 담긴 헤더에 Authorization을 꺼내서 값이 있는지 체크하고나서
        * 없으면 다음 필터로 넘기고 (인증을 요하는것이라면 다음필터에서 deny 될 것이고 , 로그인이나 회원가입경우에는 다음필터를 거쳐서 토큰을 받아야하니까)
        * "Bearer "이 아닌 것들은 걸러내고
        * 찐토큰들을 받아서 JwtTokenUtil에서 만든 메서드 중에서 토큰이 유효한지 체크한 뒤
        * 유효하다면 토큰에서 claims에 담아 놓은 memeberNumber를 이용해서
        * 인증토큰을 만들고 나서 인증토큰에 Details를 세팅한뒤
        * 인증토큰을 SecurityContextHolder안에 있는 SecurityContext에 인증을 추가
         */
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorization ==  null){
            filterChain.doFilter(request, response);
            return ;
        }

        if(!authorization.startsWith("Bearer ")){
            filterChain.doFilter(request , response);
            return ;
        }
        String token = authorization.split(" ")[1];

        if(JwtTokenUtil.isExpired(token , secretKey)){
            filterChain.doFilter(request, response);
            return ;
        }
        String memberNumber = JwtTokenUtil.getMemberNumber(token, secretKey);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberNumber , null , List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);

    }
}
