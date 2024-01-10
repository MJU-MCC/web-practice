package com.example.mcc.response;

import com.example.mcc.entity.Vote;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonPropertyOrder({"isSuccess" , "code" , "message","vote"})
@AllArgsConstructor
@Getter
public class voteResponse {
    private boolean isSuccess;
    private int code;
    private String message;
    private List<String> votes;

    public static voteResponse success(String message ){
        return new voteResponse(true , HttpStatus.OK.value(),message,null);
    }

    public static voteResponse success(String message , List<String> votes){
        return new voteResponse(true , HttpStatus.OK.value(),message,votes);
    }
}
