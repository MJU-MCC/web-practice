package com.example.mcc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //strategy를 이렇게 해야 테이블 pk값이 독립적으로 증가
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

    public Member(String memberNumber, String memberPassword , String role) {
        this.memberNumber = memberNumber;
        this.memberPassword = memberPassword;
        this.role = role;
    }

}
