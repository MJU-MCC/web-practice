package com.example.mcc.response;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class LoginResponse {

    private String message;
    private String token;

    public void setResult(String ms , String tk){
        this.message = ms ;
        this.token = tk;
    }
}
