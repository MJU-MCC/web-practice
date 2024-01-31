package com.example.mcc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{

        httpSecurity
                .csrf().disable()
                .cors();

        httpSecurity.authorizeHttpRequests()
                .antMatchers("/mcc/vote/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/mcc/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.formLogin().disable();

        httpSecurity.httpBasic().disable();

        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        httpSecurity.addFilterBefore(new JwtFilter())

        return httpSecurity.build();


    };


}
