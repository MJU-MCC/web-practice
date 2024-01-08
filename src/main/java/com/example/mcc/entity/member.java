package com.example.mcc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class member {
    //한명의 유저는 여러 투표를 가질 수 있다 , 한개의 투표는 여러 유저를 가질 수 있다.

    @Id @GeneratedValue
    private Long memberId;

    private String memberNumber;

    private String memberPassword;

    @OneToMany(mappedBy = "member")
    private List<memberVote> memberVoteList = new ArrayList<>();

    public member(String memberNumber, String memberPassword) {
        this.memberNumber = memberNumber;
        this.memberPassword = memberPassword;
    }
}
