package com.example.mcc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class member {

    @Id @GeneratedValue
    private Long memberId;

    //학번
    private String memberNumber;
    //비밀번호
    private String memberPassword;

    //권한
    private String role;


    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<participant> participantList = new ArrayList<>();

    public member(String memberNumber, String memberPassword) {
        this.memberNumber = memberNumber;
        this.memberPassword = memberPassword;
    }

    public member() {

    }
}
