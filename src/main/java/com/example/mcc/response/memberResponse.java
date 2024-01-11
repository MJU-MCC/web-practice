package com.example.mcc.response;

import com.example.mcc.entity.member;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@JsonPropertyOrder({"isSuccess" , "code" , "message","Member"})
@AllArgsConstructor
@Getter
public class memberResponse {
    private boolean isSuccess;
    private int code;
    private String message;
    private member member;
    public static memberResponse success(String message ){
        return new memberResponse(true , HttpStatus.OK.value(),message,null);
    }
    public static memberResponse success(String message , member mb){
        return new memberResponse(true , HttpStatus.OK.value(),message,mb);
    }
    public static memberResponse fail(String message){
        return new memberResponse(false , HttpStatus.BAD_REQUEST.value() ,message,null);
    }

}
