package com.example.mcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class memberVote {

    @Id
    @GeneratedValue
    private Long memberVoteId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private member member;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;

    private LocalTime createdAt;
}
