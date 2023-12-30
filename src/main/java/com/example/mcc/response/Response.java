package com.example.mcc.response;


<<<<<<< Updated upstream
import com.example.mcc.Dto.UserDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
=======
import com.example.mcc.Dto.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
>>>>>>> Stashed changes
import org.springframework.http.HttpStatus;

import java.util.Optional;

@JsonPropertyOrder({"isSuccess" , "code" , "message","user"})
@AllArgsConstructor
<<<<<<< Updated upstream
@Getter
=======
>>>>>>> Stashed changes
public class Response {
    private boolean isSuccess;
    private int code;
    private String message;
<<<<<<< Updated upstream
    private Optional<UserDto> user;

    public static Response success(String message , Optional<UserDto> user){
=======
    private Optional<User> user;

    public static Response success(String message , Optional<User> user){
>>>>>>> Stashed changes
        return new Response(true , HttpStatus.OK.value(),message,user);
    }
    public static Response fail(String message){
        return new Response(false , HttpStatus.BAD_REQUEST.value() ,message,null);
    }

}
