package com.example.mcc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    //한개의 투표글에는 여러 팀이 들어가 있다 , 한개의 팀은 여러 투표글에 있다.

    @Id @GeneratedValue
    private Long teamId;

    private String teamName;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;
}
