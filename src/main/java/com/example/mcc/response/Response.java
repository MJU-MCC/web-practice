package com.example.mcc.response;

import com.example.mcc.Dto.UserDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@JsonPropertyOrder({"isSuccess" , "code" , "message","user"})
@AllArgsConstructor
@Getter
public class Response {
    private boolean isSuccess;
    private int code;
    private String message;
    private Optional<UserDto> user;
    public static Response success(String message ){
        return new Response(true , HttpStatus.OK.value(),message,null);
    }
    public static Response success(String message , Optional<UserDto> user){
        return new Response(true , HttpStatus.OK.value(),message,user);
    }
    public static Response fail(String message){
        return new Response(false , HttpStatus.BAD_REQUEST.value() ,message,null);
    }

}
