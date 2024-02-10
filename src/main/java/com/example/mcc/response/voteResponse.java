package com.example.mcc.response;

import com.example.mcc.entity.Vote;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
public class voteResponse {

    private String message;
    private Vote votes;

}
