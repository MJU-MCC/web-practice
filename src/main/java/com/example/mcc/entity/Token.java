package com.example.mcc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@RedisHash
public class Token {

    @Id
    private String memberNumber;

    @TimeToLive(unit = TimeUnit.HOURS)
    private String refreshToken;

}
