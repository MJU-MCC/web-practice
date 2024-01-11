package com.example.mcc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

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
