package com.example.mcc.response;

import com.example.mcc.Dto.memberDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@JsonPropertyOrder({"isSuccess" , "code" , "message","Member"})
@AllArgsConstructor
@Getter
public class memberResponse {
    private boolean isSuccess;
    private int code;
    private String message;
    private Optional<memberDto> user;
    public static memberResponse success(String message ){
        return new memberResponse(true , HttpStatus.OK.value(),message,null);
    }
    public static memberResponse success(String message , Optional<memberDto> user){
        return new memberResponse(true , HttpStatus.OK.value(),message,user);
    }
    public static memberResponse fail(String message){
        return new memberResponse(false , HttpStatus.BAD_REQUEST.value() ,message,null);
    }

}
