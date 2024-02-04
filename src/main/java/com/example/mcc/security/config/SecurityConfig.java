package com.example.mcc.security.config;

import com.example.mcc.security.AuthentiFilter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .cors();
        http
                .httpBasic().disable();
        http
                .formLogin().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(new JwtTokenFilter(secretKey), UsernamePasswordAuthenticationFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/mcc/vote/regist").hasAuthority("ADMIN")            //투표 등록은 관리자만 할 수 있도록
                .antMatchers("/mcc/vote/**").hasAuthority("USER")            //투표 관련 자원은 권한이 있는지 체크하기
                .anyRequest().permitAll();                                //나머지는 모두 접근 허용
        return http.build();
    }

}
