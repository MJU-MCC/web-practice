package com.example.mcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class participant {

    @Id
    @GeneratedValue
    private Long memberVoteId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private member member;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;

    //투표한 날
    private LocalDate createdAt;
    //투표 하였는지
    private Boolean isCheck;

}
