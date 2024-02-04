package com.example.mcc.response;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class LoginResponse {

    private String message;
    private String accessToken;
    private String refreshToken;

    public void setResult(String ms , String at , String rt){
        this.message = ms ;
        this.accessToken = at;
        this.refreshToken = rt;
    }
}
