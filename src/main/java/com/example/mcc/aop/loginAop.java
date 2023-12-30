package com.example.mcc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class loginAop {

    @Pointcut("execution(* com.example.mcc.controller..*.*(..))")
    //contorller 패키지내에 있는 모든 메서드들을 적용하겠다.
    //모든 리턴타입, 패키지내에 있는 모든 메서드 , 메서드의 매개변수가 무엇이든 허용하겠다.
    private void cut(){}

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
            for(Object objects : args){
                String classSimpleName = objects.getClass().getSimpleName();
                log.info("매개변수 = {}",classSimpleName);
                log.info("객체 = {}",objects);
            }
    }

    @AfterReturning(value = "cut()" , returning = "resultobj")
    public void afterReturning(JoinPoint joinPoint , Object resultobj){
        log.info("실행한 뒤 나올때 반환 객체 = {}",resultobj);
        log.info("실행한 뒤 나올때 반환 객체의 메서드 = {}",resultobj);
    }
}
