package com.example.mcc.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration(host , port) //주의 : 두번째 인자는 int
        );
    }

    @Bean
    public RedisTemplate<String , String> redisTemplate(){
        /*
        RedisTemplate<String, String> redistemplate = new RedisTemplate<>();
        redistemplate.setConnectionFactory(redisConnectionFactory());
        redistemplate.setKeySerializer(new StringRedisSerializer());
        redistemplate.setValueSerializer(new StringRedisSerializer());
        * 코드를 아래와 같이 바꾼이유는 Redis라이브러리중에서 키와 값을 String으로 직렬화 시켜주는
        * new StringRedisSerializer()가 StringRedisTemplate 클래스에 내장되어있어서 코드가 간략해진다.
         */
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());

        return stringRedisTemplate;
    }
}
